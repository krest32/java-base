package com.krest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.krest"})
public class mongodb {
    public static void main(String[] args) {
     SpringApplication.run(mongodb.class, args);
     }
}
