package com.krest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


/**
 * 使用 Tomcat 与 WebSphere 需要继承 SpringBootServletInitializer，是服务器能够找到启动类
 */

@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoBoot  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoBoot.class,args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoBoot.class);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(DemoBoot.class);
    }


}
