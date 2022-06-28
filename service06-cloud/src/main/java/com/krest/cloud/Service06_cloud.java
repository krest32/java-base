package com.krest.cloud;


import com.krest.cloud.config.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: krest
 * @date: 2021/5/19 10:34
 * @description: 测试使用SpringCloud功能
 */
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RibbonClient(name = "service02-mybatis-plus", configuration= MySelfRule.class)
@EnableAspectJAutoProxy     // 开启Aop功能
public class Service06_cloud {
    public static void main(String[] args) {
        SpringApplication.run(Service06_cloud.class,args);
    }
}
