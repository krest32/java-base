package com.krest.mq.config;


import com.krest.mq.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: krest
 * @Date: 2020/12/24 16:36
 * @Description: 定义Mq的队列内容，绑定相应的交换机
 */
@Configuration
public class MyMQConfig {

    /**
     * 具体流程：发送消息给到延时队列，如果消息被消费掉，那么就直接删除消息，如果消息没有被消费掉，那么就将消息传递给死信队列进行处理，死信队列是一个普通队列
     */

    /**
     * 消息序列化，使用json格式，将消息转化位json格式
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    /**
     * 监听死信队列
     * @param user
     * @param channel
     * @param message
     * @throws IOException
     */
//    @RabbitListener(queues = "userMessageQueue.release.userInfo")
//    public void listener(User user, Channel channel, Message message) throws IOException {
//        System.out.println("收到过期消息:"+user.getId());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//    }

//    @RabbitListener(queues = "order.delay.queue")
//    public void createListener(ProductOrder order){
//        System.out.println("收到新的的订单消息:"+order.getId());
//    }

    /**
     * 建立延时队列 UserDelayQueue
     * @return
     */
    @Bean
    public Queue UserDelayQueue(){
        // 建立路由规则
        Map<String,Object> arguments = new HashMap<>();
        // 死信交换机
        arguments.put("x-dead-letter-exchange","userMessageQueue-event-exchange");
        // 死信路由和死信队列
        arguments.put("x-dead-letter-routing-key","userMessageQueue.release.userInfo");
        // 延时队列的信息保存时间 1s
        arguments.put("x-message-ttl",1000);
        // 建立延时队列 name：队列名称 durable：是否开启持久化 autoDelete：自动删除？
        Queue orderDelayQueue = new Queue("userMessageQueue.delay.queue", true, false, false,arguments);
        return orderDelayQueue;
    }

    @Bean
    public Queue userReleaseQueue(){
        // 普通消息队列，用来释放消息 exclusive 默认非独占（首次声明它的连接可见，并且在连接断开时自动删除）
        Queue orderDelayQueue = new Queue("userMessageQueue.release.userInfo", true, false, false);
        return orderDelayQueue;
    }

    @Bean
    public Exchange userEventExchange(){
        // 建立交换机，根据路由key发送消息 exclusive 默认非独占（首次声明它的连接可见，并且在连接断开时自动删除）
        Exchange exchange = new TopicExchange("userMessageQueue-event-exchange",true,false);
        return exchange;
    }

    /**
     * 可以通过这个交换机和路由key发送新的消息
     * @return
     */
    @Bean
    public Binding userCreateBinging(){
        Binding binding = new Binding("userMessageQueue.delay.queue",
                Binding.DestinationType.QUEUE,
                "userMessageQueue-event-exchange",
                "userMessageQueue.create.userInfo",
                null);
        return binding;
    }

    @Bean
    public Binding userReleaseBinging(){
        Binding binding = new Binding("userMessageQueue.release.userInfo",
                Binding.DestinationType.QUEUE,
                "userMessageQueue-event-exchange",
                "userMessageQueue.release.userInfo",
                null);
        return binding;
    }
}
