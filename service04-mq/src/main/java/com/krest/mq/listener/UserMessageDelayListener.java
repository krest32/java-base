package com.krest.mq.listener;

import com.krest.mq.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

/**
 * @author: krest
 * @date: 2021/5/18 13:05
 * @description:
 */
@RabbitListener(queues = "userMessageQueue.delay.queue")
public class UserMessageDelayListener {

    @RabbitHandler
    public void handleUserDelay(User user, Message message, Channel channel) throws IOException {
        if(!user.getId().equals("111")){
            // 获取消息
            System.out.println("收到需要处理的消息消息:"+user.getId());

            // 签收消息
            // DeliveryTag() 唯一标识 ID，当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag，
            // 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
            // boolean multiple：是否批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息。
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        }else{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }

    }
}
