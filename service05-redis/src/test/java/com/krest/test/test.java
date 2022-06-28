package com.krest.test;

import com.krest.redis.config.RedissonConfig;
import org.junit.Test;
import org.redisson.api.RBloomFilter;

/**
 * @author: krest
 * @date: 2021/5/21 13:33
 * @description: 测试 Redisson 布隆过滤器
 */
public class test {

    @Test
    public void test(){
        RedissonConfig redissonConfig = new RedissonConfig();
        RBloomFilter rBloomFilter = redissonConfig.rBloomFilter();
        rBloomFilter.add("123");
        rBloomFilter.add("456");
        rBloomFilter.add("789");
        // 判断是否存在
        System.out.println(rBloomFilter.contains("123"));
        System.out.println(rBloomFilter.contains("789"));
        System.out.println(rBloomFilter.contains("100"));
    }
}
