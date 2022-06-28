package com.krest.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author: krest
 * @date: 2021/5/18 21:01
 * @description:
 */
@SpringBootApplication
@MapperScan("com.krest.mybatis.mapper")
@ComponentScan(basePackages = {"com.krest"})
public class service01_mybatis {
    public static void main(String[] args) {
        SpringApplication.run(service01_mybatis.class, args);
    }
}
