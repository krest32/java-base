package com.krest.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/23 17:43
 * @description:
 */
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class
service07_es {public static void main(String[] args) {
    SpringApplication.run(service07_es.class, args);
}
}
