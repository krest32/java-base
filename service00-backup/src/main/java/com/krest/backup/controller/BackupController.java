package com.krest.backup.controller;

import com.krest.Service.Response.R;
import com.krest.Service.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 11:22
 * @description: 备用服务
 */
@Slf4j
@RestController
@RequestMapping("/mybatisplus/user")
public class BackupController {

    @PostMapping("getUserList")
    public R getUserList(){
        return R.ok().message("从备用端口启动");
    }

    @GetMapping("getUserById/{id}")
    public R getUserById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return R.ok().message("从备用端口启动");
    }

    @PostMapping("Fallback")
    public R Fallback() {
        return R.error().message("服务失败");
    }

    @GetMapping("getToken/{id}/{nickname}")
    public String getToken(@PathVariable String id,
                      @PathVariable String nickname) {
        return JwtUtils.getJwtToken(id,nickname);
    }

    @GetMapping("checkYoken/{token}")
    public Boolean checkYoken(@PathVariable String token){
        return JwtUtils.checkToken(token);
    }

    @Autowired
    ConfigurableApplicationContext applicationContext;


    @GetMapping("getConfig")
    public String getConfig(){
        String name = applicationContext.getEnvironment().getProperty("my.thread.core-size");
        log.info("name");
        return name;
    }

}
