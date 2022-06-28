package com.krest.redis.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krest.Service.Response.R;
import com.krest.redis.entity.EmployeeTbl;
import com.krest.redis.entity.vo.Comment;
import com.krest.redis.entity.vo.EmployRedisVo;
import com.krest.redis.entity.vo.KeyValue;
import com.krest.redis.service.EmployeeTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
@Api(tags = "Redis功能基本测试")
@RestController
@RequestMapping("/redis/employee-tbl")
public class EmployeeTblController {


    @Autowired
    private EmployeeTblService employeeTblService;

    /**
     *  JdkSerializationRedisSerializer 客户端
     *  数据存储进入Redis,数据不可读
     */
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 存储在redis的数据是String,数据可读
     */
    @Autowired
    private StringRedisTemplate stringRedis;

    /**
     * redisoon 分布式锁客户端,功能相对来说稍微简单
     */
    @Autowired
    private Redisson redisson;

    /**
     * Redisson 布隆过滤器客户端
     */
    @Autowired
    private RBloomFilter rBloomFilter;


    @ApiOperation("Redisso客户端，实现分布式锁")
    @GetMapping("RedLock")
    public R RedisLockDemo(){
        String lockKey = "lockKey";
        // 新建锁
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //加锁(超时默认30s), 实现锁续命的功能(后台启动一个timer, 默认每10s检测一次是否持有锁)
            redissonLock.lock();
            Thread.sleep(2000);
            //------ 执行业务逻辑 ----start------
            int stock = Integer.valueOf(stringRedis.opsForValue().get("RedisLockTestStock"));
            if (stock > 0) {
                int newStock = stock - 1;
                //执行业务操作减库存
                stringRedis.opsForValue().set("RedisLockTestStock", newStock+"");
                System.out.println(Thread.currentThread().getName()+"扣减库存成功, 剩余库存:" + newStock);
                return R.ok().message("成功扣减库存");
            } else {
                System.out.println("库存已经为0，不能继续扣减");
                return R.ok().message("库存已经为0，不能继续扣减");
            }
            //------ 执行业务逻辑 ----end------
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            redissonLock.unlock();
        }
        return R.error().message("分布式锁执行错误");
    }

    /**
     *  利用redis的缓存中设置令牌
     */
    @ApiOperation("添加令牌")
    @GetMapping("createToken/{tokenKey}/{token}")
    public R createToken(@PathVariable String tokenKey,
                         @PathVariable String token){
        //String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(tokenKey,token);
        return R.ok();
    }

    /**
     * 用于接口的幂等性验证，Lua脚本，保证命令验证删除的原子性
     * @param tokenKey
     * @param token
     * @return
     */
    @ApiOperation("删除令牌")
    @DeleteMapping("deleteToken/{tokenKey}/{token}")
    public R deleteToken(@PathVariable String tokenKey,
                         @PathVariable String token) {
        String script = "if redis.call('get', KEYS[1])==ARGV[1] " +
                "then return redis.call('del',KEYS[1]) else return 0 end";
        //令牌验证要通过,令牌的验证和删除必须保证原子性：使用脚本进行删除，防止用户多次点击，重复提交，0 校验失败
        Long result = (Long) redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class),
                Arrays.asList(tokenKey), token);

        if (result == 0L) {
            //删除令牌失败
            System.out.println("删除令牌失败");
            return R.error();
        } else {
            System.out.println("令牌验证成功");
            return R.ok();
        }
    }



    /**
     *  @Cacheable(value = "data")将结果缓存到内存中
     * @return data
     */
    @ApiOperation("获取所有雇员信息")
    @Cacheable(value = "data")
    @GetMapping("getAll")
    public R getAll(){
        return R.ok().data("data",employeeTblService.list(null));
    }



    @ApiOperation("删除Redis中的Key")
    @DeleteMapping("deleteKey/{key}")
    public R deleteKey(@PathVariable String key){
        Boolean flag = redisTemplate.delete(key);
        if(flag){
            return R.ok();
        }
        return R.error();

    }

    @ApiOperation("使用Hash")
    @GetMapping("useHash")
    public R useHash(){
        List<EmployeeTbl> list = employeeTblService.list(null);
        EmployRedisVo employRedisVo = new EmployRedisVo();
        employRedisVo.setEmployeeTblList(list);
        // 将信息存储到redis中，并且设定事件为5分钟
        redisTemplate.opsForValue().set("employVo",employRedisVo,5,TimeUnit.MINUTES);
        return R.ok().data("singNum",employRedisVo.getSingin());
    }


    /**
     * 将评论存储到Redis当中
     */
    @ApiOperation("增加评论功能")
    @GetMapping("addSingInt/{staredId}/{userId}/{comment}")
    public R addSingInt(@PathVariable String staredId,
                        @PathVariable String userId,
                        @PathVariable String comment){
        // 对一个key进行绑定化操作
        BoundHashOperations boundHashOps = redisTemplate.boundHashOps(staredId);
        EmployeeTbl employeeTbl = employeeTblService.getById(userId);
        Comment commentTemp = new Comment();
        commentTemp.setComment(comment);
        commentTemp.setEmployeeTbl(employeeTbl);
        commentTemp.setDate(new Date());
        // 需要转换为字符串进行存储
        String s = JSON.toJSONString(commentTemp);
        System.out.println(s);
        boundHashOps.put(userId,s);
        return R.ok();
    }



    /**
     * 得到hash的数据存款结构
     * @return
     */
    @ApiOperation("根据找到Redis中的信息")
    @GetMapping("getRedisValue/{checkid}")
    public R getRedisValue(@PathVariable String checkid){
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(checkid);
        System.out.println(checkid);
        List<String> values = boundHashOperations.values();
        if (values!=null && values.size()>0){
            List<Comment> list = values.stream().map(obj->{
                String str = (String) obj;
                System.out.println("String"+str);
                Comment comment = JSON.parseObject(str,Comment.class);
                System.out.println("comment"+comment);
                return comment;
            }).collect(Collectors.toList());
            return R.ok().data("data",list);
        }
        return R.error().code(401).message("未找到数据");
    }

    /**
     * @CacheEvict(value = "data")
     * 调用该方法会删除data的数据
     * @param employeeTbl
     * @return
     */
    @ApiOperation("添加新信息")
    @CacheEvict(value = "data")
    @PostMapping("addNew")
    public R addNew(@RequestBody EmployeeTbl employeeTbl){
        boolean flag = employeeTblService.save(employeeTbl);
        if (flag){
            rBloomFilter.add(employeeTbl.getName());
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("测试布隆过滤器,根据name查询数据")
    @GetMapping("getByName/{name}")
    public R getByName(@PathVariable String name){
        if (rBloomFilter.contains(name)){
            QueryWrapper<EmployeeTbl> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name",name);
            EmployeeTbl data =(EmployeeTbl) employeeTblService.getObj(queryWrapper);
            return R.ok().data("data",data);
        }
        return R.error().message("布隆过滤器判断数据不存在");
    }


    /**
     * @Cacheable(value = "employCache",key = "#id")
     * 将对应id的数据信息缓存到redis中
     * @param id
     * @return
     */
    @ApiOperation("根据id查找用户")
    @Cacheable(value = "employCache",key = "#id")
    @GetMapping("getById/{id}")
    public R getById(@PathVariable String id){
        EmployeeTbl employeeTbl= employeeTblService.getById(id);
        if (employeeTbl!=null){
            return R.ok().data("employeeTbl",employeeTbl);
        }
        return R.error();
    }

    /**
     * @CacheEvict(value = "employCache",key = "#employeeTbl.id")
     * 更新数据库中对应id的信息，会删除对应的缓存的数据，只有当重新查询该数据时，信息才会再次加入到缓存中
     */
    @ApiOperation("根据Id更新用户")
    @CacheEvict(value = "employCache",key = "#employeeTbl.id")
    @PostMapping("updateById")
    public R updateById(@RequestBody EmployeeTbl employeeTbl){
        boolean flag = employeeTblService.updateById(employeeTbl);
        if (flag){
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("向Redis中添加键值对信息")
    @PostMapping("setString")
    public R setString(@RequestBody KeyValue data){
        if(data.getKey()!=null && data.getValue()!=null){
            redisTemplate.opsForValue().set(data.getKey(),data.getValue(),5, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error();
        }
    }
}

