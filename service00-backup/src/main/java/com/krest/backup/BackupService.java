package com.krest.backup;

import jdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/19 11:20
 * @description:
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BackupService {
    public static void main(String[] args) {
        SpringApplication.run(BackupService.class,args);
    }
}
