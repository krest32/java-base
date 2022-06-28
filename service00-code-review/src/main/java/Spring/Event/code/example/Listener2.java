package Spring.Event.code.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/4/23 13:29
 * @description: 用来监听事件
 */
@Component
public class Listener2 {

    @EventListener
    public void sayHello(Event notifyEvent){
        System.out.println("DemoListener获取到了监听消息:"+notifyEvent.getMsg());
        System.out.println("监听2号");
        SayHello sayHello = new SayHello();
        sayHello.say();
    }
}