package com.krest.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/17 23:34
 * @description: 测试 mybatis Plus
 */

@EnableDiscoveryClient
@MapperScan("com.krest.mybatisplus.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class service02_mybatis_plus  {
    public static void main(String[] args) {
        SpringApplication.run(service02_mybatis_plus.class, args);
    }
}
