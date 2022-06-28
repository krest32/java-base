package com.krest.Service.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/5/18 18:50
 * @description:
 */

@Component
@Data
@ConfigurationProperties(prefix = "my.thread")
public class ThreadPoolConfigProperties {
    Integer coreSize=20;
    Integer maxSize=50;
    Integer keepAliveTime=3000;
}
