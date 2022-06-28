package com.krest.es.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.krest.es.entity.User;
import com.krest.es.service.EsService;
import com.krest.Service.Response.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/23 17:52
 * @description:
 */
@RestController
@RequestMapping("/es/service")
public class EsController {



    @Autowired
    private EsService esService;


    @ApiOperation("1. 测试远程服务调用ById")
    @GetMapping("feignGetById/{id}")
    public R feignGetById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return esService.getUserById(id);
    }

    @ApiOperation("2. 将设定id的信息存储进入到Es中")
    @PostMapping("savaToEs/{id}")
    public R savaToEs(@PathVariable String id) throws ExecutionException, InterruptedException, IOException {
        // 通过远程来获取得到对象
        R result = esService.getUserById(id);
        Object  object= result.getData().get("user");
        // 使用Alibaba的工具实现对于对象类型的转化
        User user = JSON.parseObject(JSON.toJSONString(object), new TypeReference<User>() { });


        // 将对象的数据类型存储进入到 Es 中
        return  esService.savaToEs(user);
    }

    @ApiOperation(value = "3. 从Es中删除信息")
    @DeleteMapping("deleteEsUser/{id}")
    public R deleteEsUser(@PathVariable String id) throws IOException {
        return  esService.deleteEsUser(id);
    }

}
