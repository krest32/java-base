package com.krest.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krest.Service.entity.User;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-17
 */
public interface UserService extends IService<User> {

    User getUserById(String id) throws ExecutionException, InterruptedException;
}
