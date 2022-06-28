package com.krest.redis.mq;

import redis.clients.jedis.Jedis;

/**
 * 基于List实现
 */
public class ListMQTest1 {

    public static void main(String[] args){
        // 启动一个线程作为消费者
        new Thread(() -> consumer()).start();
        // 生产者
        producer();
    }
    /**
     * 生产者
     */
    public static void producer() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 推送消息
        jedis.lpush("mq", "Hello, List.");
    }

    /**
     * 消费者
     */
    public static void consumer() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 消费消息
        while (true) {
            // 获取消息
            String msg = jedis.rpop("mq");
            if (msg != null) {
                // 接收到了消息
                System.out.println("接收到消息：" + msg);
            }
        }
    }
}
