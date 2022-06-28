package com.krest.sqlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/6/22 16:00
 * @description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.krest"})
@MapperScan("com.krest.mapper")
public class Service14_sqlserver {
    public static void main(String[] args) {
        SpringApplication.run(Service14_sqlserver.class,args);
    }
}
