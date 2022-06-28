package com.krest.demo.starter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoServiceAutoConfiguration {

    private DemoProperties demoProperties;

    //通过构造方法注入配置属性对象 DemoProperties
    public DemoServiceAutoConfiguration(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    //实例化HelloService并载入Spring IoC容器
    @Bean
    @ConditionalOnMissingBean
    public DemoService helloService(){
        return new DemoService(demoProperties.getName(),demoProperties.getAddress());
    }
}
