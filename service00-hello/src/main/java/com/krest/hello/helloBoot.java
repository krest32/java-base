package com.krest.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author: krest
 * @date: 2021/6/23 10:54
 * @description: Tomcat启动部署测试
 */
@ComponentScan(basePackages = "com.krest")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class helloBoot  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(helloBoot.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(helloBoot.class);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(helloBoot.class);
    }

}
