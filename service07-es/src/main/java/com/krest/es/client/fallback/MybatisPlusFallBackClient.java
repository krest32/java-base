package com.krest.es.client.fallback;


import com.krest.Service.Response.R;
import com.krest.es.client.MybatisPlusClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 11:11
 * @description: MybatisPlusClient 远程调用熔断方法
 */
@Component
public class MybatisPlusFallBackClient implements MybatisPlusClient {

    @Override
    public R getUserList() {
        return R.error().message("获取用户列表失败");
    }

    @Override
    public R getUserById(String id) throws ExecutionException, InterruptedException {
        return R.error().message("获取用户失败");
    }
}
