# Spring事件监听机制



### 概述

​		在分布式的应用中，通常涉及其他组件的通知变更都是通过MQ接收消息，然后将消息转发到相关的模块进行业务逻辑的修改。但是这种本地化的消息通知机制，使用spring提供的消息驱动模型很好的解决问题，spring事件本来就是为了解决这类的问题而产生的。

### 实现原理

​		Spring事件采用了观察者模式

###  事件机制介绍

Spring事件机制的介绍分为三个模块进行讲解，如下图所示：

![image-20210423130651885](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210423130651.png)



#### 事件驱动

事件驱动模型的核心构件通常包含

+ 事件源：负责产生事件的对象
+  事件监听器：负责处理事件的对象
+ 事件：或者称为事件对象，是事件源和事件监听器之间的信息桥梁。是整个事件模型驱动的核心



#### Spring事件支持

Spring对于事件机制的支持对应消息驱动模型中的监听器、事件源、事件等概念

![image-20210423130823721](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210423130823.png)

##### ApplicationListener监听器

![image-20210423130917225](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210423130917.png)

##### ApplicationEventPublisher事件源

实现了发送事件、或者发送包装事件原理

##### ApplicationEvent 事件

仅仅是一个表征事件类型和携带事件信息的基类

##### ApplicationEventMulticaster 事件广播器

广播器也就是事件模型中的事件收集器，总是需要一个人来维护发送者和接受者之间的关系，广播器就是维护这样的角色的。
继承图：从继承图中可以粗略的了解广播器的角色，除了基本的监听器集合的维护之外还提供了一些同步异步处理等功能

![image-20210423131128484](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210423131128.png)

#### Spring 处理流程

​		Spring处理流程最主要的就是维护广播器和监听器信息，通过将所有的监听者添加到广播器监听者集合中，事件源发送事件的时候，广播器能够找到将匹配到的事件的类型的所有监听者，然后通过同步或者异步的方式发送消息。

![image-20210423131258190](https://duxin2010.oss-cn-beijing.aliyuncs.com/20210423131258.png)



### 代码实现

#### 事件三要素
1、定义一个事件（火灾事件、碰撞事件、收到信息事件。。。）

2、有一个事件的触发者 在合适的时机去触发这个事件，例如 触发起火 导致温度传感器上升、导致系统发生报警

这个例子中处处都有事件发布者和事件处理人的影子；

3、必须要有一个事件处理者时刻监听目前有没有新的事件要处理

#### 测试代码

#####  事件发布类

~~~java
/**
 * @author: krest
 * @date: 2021/4/23 13:20
 * @description: 实现ApplicationContextAware接口，用于发布事件
 */
@Component
public class NotifyPublisher implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx= applicationContext;
    }
    public void publishEvent(String hh,String msg) {
        ctx.publishEvent(new NotifyEvent(hh,msg));
    }
}
~~~

##### 事件监听类

~~~
使用@Component 和 @EventListener 用来监听事件的发生
~~~

~~~java
/**
 * @author: krest
 * @date: 2021/4/23 13:29
 * @description: 用来监听事件
 */
@Component
public class MqListener {
    
    @EventListener
    public void sayHello(NotifyEvent notifyEvent){
        String msg = notifyEvent.getMsg();
        System.out.println("DemoListener获取到了监听消息:"+msg);
    }
}
~~~

##### 自定义事件

~~~
继承ApplicationEvent，使这个事件可以被系统获取
~~~

~~~Java
/**
 * @author: krest
 * @date: 2021/4/23 13:18
 * @description: 自定义事件
 */
public class NotifyEvent extends ApplicationEvent {

    @Getter
    private String msg;

    public NotifyEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
~~~

##### 配置类

```
/**
 * @author: krest
 * @date: 2021/4/23 14:19
 * @description:
 */
@Configuration
@ComponentScan("springevent")
public class EventConfig {
}
```

##### 测试类

~~~java
/**
 * @author: krest
 * @date: 2021/4/23 14:04
 * @description: 测试类
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);

        NotifyPublisher publish = context.getBean(NotifyPublisher.class);
        publish.publishEvent("小杜","你好");
        context.close();
    }
}
~~~

