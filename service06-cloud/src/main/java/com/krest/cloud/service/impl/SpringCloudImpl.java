package com.krest.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.krest.Service.Response.R;
import com.krest.Service.entity.User;
import com.krest.Service.exception.myException;
import com.krest.cloud.client.MybatisPlusClient;
import com.krest.cloud.service.SpringCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 10:51
 * @description:
 */
@Service
public class SpringCloudImpl implements SpringCloud {
    @Autowired
    private MybatisPlusClient mybatisPlusClient;

    @Override
    public R getUserById(String id) throws ExecutionException, InterruptedException {
        return mybatisPlusClient.getUserById(id);
    }
    @Override
    public R feignGetAll() {
        return mybatisPlusClient.getUserList();
    }

    @Override
    public User feignGetById(String id) throws ExecutionException, InterruptedException {
        User user = new User();
        try{
            user = mybatisPlusClient.feignGetUserById(id);
        }catch (Exception e){
            throw new myException(500,"cuowe");
        }
        return user;
    }
}
