package com.krest.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.krest.Service.Response.R;
import com.krest.kafka.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: krest
 * @date: 2021/6/1 07:43
 * @description:
 */
@Api(tags = "kafka测试")
@RequestMapping("/app/kafka")
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String topic = "test1";

//    @ApiOperation("消息发送方")
//    @GetMapping("send/{input}")
//    @Transactional
//    public R sendToKafka(@PathVariable String input){
//        // 事务支持
//        kafkaTemplate.executeInTransaction(t ->{
//           t.send(topic,input);
//           if("error".equals(input)){
//               throw new RuntimeException("input error");
//           }
//           t.send(topic,input+"anthor");
//           return true;
//        });
//
//        return R.ok();
//    }


//    @ApiOperation("测试事务")
//    @GetMapping("Transactional/{input}")
//    @Transactional(rollbackFor = RuntimeException.class)
//    public R Transactional(@PathVariable String input){
//        // 事务支持
//        kafkaTemplate.send(topic,input);
//        if("error".equals(input)){
//            throw new RuntimeException("input error");
//        }
//        kafkaTemplate.send(topic,input+"anthor");
//        return R.ok();
//    }

    @ApiOperation("测试发送对象")
    @PostMapping("sendObject")
    public R sendObject(@RequestBody User user) {
        kafkaTemplate.send(topic,user);
        return R.ok();
    }

    @ApiOperation("测试发送对象字符串")
    @PostMapping("sendObjectString")
    public R sendObjectString(@RequestBody User user) {
        String s = JSON.toJSONString(user);
        kafkaTemplate.send(topic,s);
        System.out.println(s);
        return R.ok();
    }

}
