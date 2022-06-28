package com.krest.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/6/22 10:57
 * @description: xml返回报文测试
 */

@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class service12_xml {
    public static void main(String[] args) {
        SpringApplication.run(service12_xml.class,args);
    }
}
