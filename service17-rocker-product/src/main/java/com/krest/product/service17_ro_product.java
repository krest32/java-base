package com.krest.product;

import com.krest.product.config.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.krest"})
public class service17_ro_product  implements CommandLineRunner {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //main函数，这里其实不需要 ....
    public static void main(String[] args) {
        SpringApplication.run(service17_ro_product.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //发送消息
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.convertAndSend("test-topic-2",
                new OrderPaidEvent("orderId-0001", 88));
    }
}
