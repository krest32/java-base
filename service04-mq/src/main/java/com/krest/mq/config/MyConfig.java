package com.krest.mq.config;

import com.krest.mq.listener.UserMessageDelayListener;
import com.krest.mq.listener.UserMessageReleaseListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: krest
 * @date: 2021/5/18 13:16
 * @description: 向Spring中添加自定义的两个Mq监听器，让他们可以自动的运行
 */
@Configuration
public class MyConfig {

    @Bean
    public UserMessageDelayListener getUserMessageDelayListener(){
        return new UserMessageDelayListener();
    }

    @Bean
    public UserMessageReleaseListener userMessageReleaseListener(){
        return new UserMessageReleaseListener();
    }
}
