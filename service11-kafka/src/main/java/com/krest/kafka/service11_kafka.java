package com.krest.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/6/1 07:41
 * @description:
 */
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class service11_kafka {
    public static void main(String[] args) {
        SpringApplication.run(service11_kafka.class,args);
    }
}
