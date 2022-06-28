package com.krest.Gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krest.Gateway.entity.ReturnData;


import com.krest.Gateway.utils.JwtUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author: krest
 * @date: 2021/5/19 13:59
 * @description:
 */
@Component
//读取 yml 文件下的 org.my.jwt
@ConfigurationProperties("org.my.jwt")
@Data
@Slf4j
public class JwtTokenFilter implements GlobalFilter, Ordered {

    private String[] skipAuthUrls;

    private ObjectMapper objectMapper;

    public JwtTokenFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().getPath();

        //跳过不需要验证的路径
        if(null != skipAuthUrls && Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }

        // 建立返回的响应
        ServerHttpResponse resp = exchange.getResponse();
        String token;
        try {
            // 从cookies获取token
             token = exchange.getRequest().getCookies().getFirst("token").getValue();
        }catch (Exception e){
            System.out.println("有token");
            //没有token
            return authErro(resp,"请登陆");
        }

        //有token
        try {
            if(JwtUtils.checkToken(token)){
                return chain.filter(exchange);
            }else{
                return authErro(resp, "认证失败");
            }
        }catch (Exception e){
            return authErro(resp, "认证失败");
        }
    }

    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authErro(ServerHttpResponse resp,String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        ReturnData returnData = new ReturnData(403, mess, mess);
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(returnData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }


    @Override
    public int getOrder() {
        return -100;
    }

}
