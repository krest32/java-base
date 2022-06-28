package com.krest.Gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/19 15:59
 * @description: 限流规则处理器
 */
@Configuration
public class KeyResolverConfiguration {

    /**
     * ip 限流
     * @return
     */
//    @Bean
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
//    }
//
//    public static String getIpAddr(ServerHttpRequest request) {
//        HttpHeaders headers = request.getHeaders();
//        List<String> ips = headers.get("X-Forwarded-For");
//        String ip = "127.0.0.1";
//        if (ips != null && ips.size() > 0) {
//            ip = ips.get(0);
//        }
//        return ip;
//    }

    /**
     * 接口限流
     * @return
     */
//    @Bean
//    KeyResolver apiKeyResolver() {
//        return exchange ->
//                Mono.just(exchange.getRequest().getPath().value());
//    }

    /**
     * 参数限流
     */
//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange ->
//                Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
//    }

    /**
     * 路径限流
     * @return
     */
    @Bean
    KeyResolver pathKeyResolver() {
        return exchange ->
                Mono.just(exchange.getRequest().getURI().getPath());
    }
}
