package com.krest.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/18 14:28
 * @description:
 */
@MapperScan("com.krest.redis.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class service05_redis {
    public static void main(String[] args) {
        SpringApplication.run(service05_redis.class,args);
    }
}
