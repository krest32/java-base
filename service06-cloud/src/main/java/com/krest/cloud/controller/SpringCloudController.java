package com.krest.cloud.controller;

import com.krest.Service.Response.R;
import com.krest.Service.entity.User;
import com.krest.cloud.service.SpringCloud;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 10:50
 * @description: Spring Cloud 服务测试
 */
@Api(tags = "SpringCloud服务测试接口")
@RestController
@RequestMapping("/spring/cloud")
public class SpringCloudController {

    @Autowired
    private SpringCloud springCloud;

    @ApiOperation("1. 测试服务启动")
    @GetMapping("hello")
    public R hello(){
        return R.ok().data("hello","hello");
    }

    @ApiOperation("2. 测试远程服务调用ById")
    @GetMapping("feignGetById/{id}")
    public User feignGetById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return springCloud.feignGetById(id);
    }

    @ApiOperation("3. 测试远程服务调用getAll")
    @PostMapping("feignGetAll")
    public R feignGetAll(){
        return springCloud.feignGetAll();
    }

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @GetMapping("getConfig")
    public String getConfig(){
        String name = applicationContext.getEnvironment().getProperty("my.thread.core-size");
        System.out.println(name);
        return name;
    }
}
