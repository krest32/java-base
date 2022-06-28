package com.krest.excle;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/18 00:52
 * @description:
 */

@EnableDiscoveryClient
@MapperScan("com.krest.excle.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class service03_excle {
    public static void main(String[] args) {
        SpringApplication.run(service03_excle.class, args);
    }
}
