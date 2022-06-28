package com.krest.sqlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/6/22 16:00
 * @description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.krest"})
@MapperScan("com.krest.sqiserver.mapper")
public class service13_sqlserver {
    public static void main(String[] args) {
        SpringApplication.run(service13_sqlserver.class,args);
    }
}
