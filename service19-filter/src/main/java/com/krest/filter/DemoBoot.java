package com.krest.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * 过滤器
 */
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoBoot {
    public static void main(String[] args) {
        SpringApplication.run(DemoBoot.class,args);
    }
}
