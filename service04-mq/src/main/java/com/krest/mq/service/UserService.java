package com.krest.mq.service;

import com.krest.Service.Response.R;
import com.krest.mq.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
public interface UserService extends IService<User> {

    R clearMsg();
}
