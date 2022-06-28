package com.krest.es.client;

import com.krest.Service.Response.R;
import com.krest.es.client.fallback.MybatisPlusFallBackClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 10:56
 * @description: 远程调用包
 */
@Component
@FeignClient(value = "service02-mybatis-plus",fallback = MybatisPlusFallBackClient.class)
public interface MybatisPlusClient {
    /**
     * 建立远程调用的接口
     * @return R
     */
    @PostMapping("/mybatisplus/user/getUserList")
    public R getUserList();

    @GetMapping("/mybatisplus/user/getUserById/{id}")
    public R getUserById(@PathVariable("id") String id) throws ExecutionException, InterruptedException ;

}
