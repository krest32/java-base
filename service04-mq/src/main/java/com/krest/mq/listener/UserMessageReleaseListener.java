package com.krest.mq.listener;

import com.krest.mq.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author: krest
 * @date: 2021/5/18 13:28
 * @description:
 */
@RabbitListener(queues = "userMessageQueue.release.userInfo")
public class UserMessageReleaseListener {

    @RabbitHandler
    public void handleUserRelease(User user, Message message, Channel channel) throws IOException {
        System.out.println("收到释放的信息:"+user.getId());
        // b 关闭自动签收功能
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
