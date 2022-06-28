package com.krest.sqlserver.controller;

import com.krest.Service.Response.R;
import com.krest.sqlserver.entity.User;
import com.krest.sqlserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/6/22 16:29
 * @description:
 */
@Api(tags = "测试SqlServer")
@RestController
@RequestMapping("/mybatisplus/user")
public class SqlServerController {

    @Autowired
    private UserService userService;

    @ApiOperation("列出所有")
    @GetMapping("getAll")
    public R getAll(){
        List<User> list = userService.list();
        return R.ok().data("list",list);
    }

    @ApiOperation("根据Id查询信息")
    @GetMapping("getById/{id}")
    public R getById(@PathVariable String id){
        User user = userService.getById(id);
        return R.ok().data("user",user);
    }


    @ApiOperation("更新用户信息")
    @PostMapping("update")
    public R update(@RequestBody User user){
        int i =  userService.update(user);
        return R.ok();
    }

    @ApiOperation("插入新的数据")
    @PostMapping("insert")
    public R insert(@RequestBody User user){
        int i;
        try{
            User temp = new User("123","123");
            System.out.println(temp);
            System.out.println(user);
             i =  userService.insert(user);
        }catch (Exception e){
            return R.error().data("error",e.getMessage());
        }

        return R.ok().data("i",i);
    }

    @ApiOperation("根据Id删除数据")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id){
        int i =  userService.delete(id);
        return R.ok();
    }


    @ApiOperation("统计条数")
    @PostMapping("count/{id}")
    public R count(@RequestBody User user){
        int count =  userService.count(user);
        return R.ok().data("count",count);
    }


    @ApiOperation("条件分页查询")
    @PostMapping("pageQuery")
    public R queryPage(@RequestParam(name = "page") Long page,
                       @RequestParam(name = "limit") Long limit,
                       @RequestBody(required = false) User user){
        R r = userService.pageQuery(page, limit, user);
        return r;
    }


    @ApiOperation("条件查询不分页")
    @PostMapping("QueryList")
    public R QueryList(@RequestBody User user){
        R r = userService.queryList(user);
        return r;
    }







}
