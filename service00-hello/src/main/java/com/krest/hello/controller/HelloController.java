package com.krest.hello.controller;

import com.krest.demo.starter.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: krest
 * @date: 2021/6/23 10:55
 * @description:
 */

@RequestMapping(value = "/api/v1/documentService")
@RestController
public class HelloController {

    @PostMapping
    public  String hello(@RequestBody String xml){
        System.out.println(xml);
        return "hello";
    }


    @Autowired
    private DemoService demoService;

    @GetMapping("/say")
    public String sayHello(){
        return demoService.sayHello();
    }
}
