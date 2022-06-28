package com.krest.kafka.components;

import com.alibaba.fastjson.JSON;
import com.krest.kafka.entity.User;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/6/1 07:51
 * @description:
 */
@Component
public class kafkaListener {

    @KafkaListener(id = "my", topics = "test1",groupId = "group.demo")
    public void KafkaListener(String output){
        User user = JSON.parseObject(output, User.class);
        System.out.println("kafka消息："+user.getName());
    }

}
