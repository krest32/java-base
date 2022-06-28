package com.krest.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @Auther: krest
 * @Date: 2020/11/12 22:59
 * @Description: 用来配置Es的设置
 */
@Configuration
public class ElasticSearchHighLevelClientConfig extends AbstractElasticsearchConfiguration {

    /**
     *  生成一个操作Es的对象
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .build();

        System.out.println(RestClients.create(clientConfiguration).rest());
        return RestClients.create(clientConfiguration).rest();
    }
}

