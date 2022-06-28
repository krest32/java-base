package com.krest.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: krest
 * @date: 2021/5/18 11:49
 * @description: 测试 RabbitMQ 延时队列已经消息监听功能，发送到死信队列中
 */
@EnableRabbit
@MapperScan("com.krest.mq.mapper")
@ComponentScan(basePackages = {"com.krest"})
@SpringBootApplication
public class service04_mq {
    public static void main(String[] args) {
        SpringApplication.run(service04_mq.class,args);
    }
}
