package com.krest.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("bob")
    public String bob(){
        return "bob";
    }
}
