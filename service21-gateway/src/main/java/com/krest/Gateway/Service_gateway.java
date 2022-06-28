package com.krest.Gateway;

import com.krest.Gateway.config.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 启动使用网关，并且注册到 NACOS 中，可以当
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
// 设定某个服务的负载均和方式
@RibbonClient(name = "service02-mybatis-plus", configuration= MySelfRule.class)
public class Service_gateway {
    public static void main(String[] args) {
        SpringApplication.run(Service_gateway.class, args);
    }
}
