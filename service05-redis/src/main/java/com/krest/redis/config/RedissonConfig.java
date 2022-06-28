package com.krest.redis.config;

import org.apache.poi.ss.formula.functions.T;
import org.redisson.Redisson;
import org.redisson.RedissonBloomFilter;
import org.redisson.api.RBloomFilter;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: krest
 * @date: 2021/5/18 18:01
 * @description: Redisson 配置项
 * RedisRedisson是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。
 * 它不仅提供了一系列的分布式的Java常用对象，还提供了许多分布式服务。
 * 其中包括(BitSet, Set, Multimap, SortedSet, Map, List, Queue, 
 * BlockingQueue, Deque, BlockingDeque, Semaphore, Lock, 
 * AtomicLong, CountDownLatch, Publish / Subscribe, 
 * Bloom filter(布隆过滤器), Remote com.krest.cloud.service, Spring cache, Executor com.krest.cloud.service, 
 * Live Object com.krest.cloud.service, Scheduler com.krest.cloud.service)
 *
 * Redisson提供了使用Redis的最简单和最便捷的方法。
 * Redisson的宗旨是促进使用者对Redis的关注分离（Separation of Concern），
 * 从而让使用者能够将精力更集中地放在处理业务逻辑上。
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        //单机版
        config.useSingleServer().setAddress("redis://121.196.111.229:6379").setDatabase(0);

        //集群版
      //  threadpool.useClusterServers()
        //        .addNodeAddress("redis://192.168.1.1:8001")
          //      .addNodeAddress("redis://192.168.1.1:8002")
            //    .addNodeAddress("redis://192.168.1.2:8001")
              //  .addNodeAddress("redis://192.168.1.2:8002")
                //.addNodeAddress("redis://192.168.1.3:8001")
                // .addNodeAddress("redis://192.168.1.3:8002");
        return (Redisson) Redisson.create(config);
    }

    /**
     * 会事先在Redis中建立一个布隆过滤器,且能够存放size个key,数字越大,误判率越小,
     * 测试过程中数字不要太大,否则容易造成客户端卡顿
     * @return
     */
    @Bean
    public RBloomFilter rBloomFilter(){
        // 初始化预期插入的数据量为10000000和期望误差率为0.01
        String name="krest-redisson-bloomfilter";
        Integer size=10000;
        Double fbb=0.01;

        Redisson redisson = redisson();
        RBloomFilter seqIdBloomFilter = redisson.getBloomFilter(name);

        seqIdBloomFilter.tryInit(size, fbb);
        return seqIdBloomFilter;
    }

}
