package com.krest.redis.mq;

import redis.clients.jedis.Jedis;

public class ListMQTest2 {

    public static void main(String[] args) throws InterruptedException {
        // 消费者
        new Thread(() -> bConsumer()).start();
        // 生产者
        producer();
    }
    /**
     * 生产者
     */
    public static void producer() throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 推送消息
        jedis.lpush("mq", "Hello, Java.");
        Thread.sleep(1000);
        jedis.lpush("mq", "message 2.");
        Thread.sleep(2000);
        jedis.lpush("mq", "message 3.");
    }
    /**
     * 消费者（阻塞版）
     */
    public static void bConsumer() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        while (true) {
            // 阻塞读
            for (String item : jedis.brpop(0,"mq")) {
                // 读取到相关数据，进行业务处理
                System.out.println(item);
            }
        }
    }
}
