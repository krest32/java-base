package com.krest.toImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ToImage {
    public static void main(String[] args) {
        SpringApplication.run(ToImage.class,args);
    }
}
