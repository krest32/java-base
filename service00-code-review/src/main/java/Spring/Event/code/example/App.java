package Spring.Event.code.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: krest
 * @date: 2021/4/23 14:04
 * @description: 测试类 Spring事件机制
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        NotifyPublisher publish = context.getBean(NotifyPublisher.class);
        publish.publishEvent("小杜","你好");
        context.close();
    }
}
