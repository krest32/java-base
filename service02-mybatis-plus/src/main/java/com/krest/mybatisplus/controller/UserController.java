package com.krest.mybatisplus.controller;


import com.krest.Service.Response.R;
import com.krest.Service.entity.User;
import com.krest.mybatisplus.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/mybatisplus/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("getUserList")
    public R getUserList(){
        List<User> list = new ArrayList<>(userService.list(null));
        return R.ok().data("list",list);
    }

    @GetMapping("getUserById/{id}")
    public R getUserById(@PathVariable String id) throws ExecutionException, InterruptedException {
        User userById = userService.getUserById(id);
        return R.ok().data("user",userById);
    }


    @GetMapping("feignGetUserById/{id}")
    public User feignGetUserById(@PathVariable String id) throws ExecutionException, InterruptedException {
        User userById = userService.getUserById(id);
        return userById;
    }
}

