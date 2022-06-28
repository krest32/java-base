package com.krest.cloud.service;

import com.krest.Service.Response.R;
import com.krest.Service.entity.User;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/19 10:50
 * @description:
 */
public interface SpringCloud {
    R getUserById(String id) throws ExecutionException, InterruptedException;

    R feignGetAll();

    User feignGetById(String id) throws ExecutionException, InterruptedException;
}
