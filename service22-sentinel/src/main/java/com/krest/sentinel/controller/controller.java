package com.krest.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: krest
 * @date: 2021/5/21 18:11
 * @description:
 */
@RestController
@RequestMapping("/spring/service")
public class controller {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
