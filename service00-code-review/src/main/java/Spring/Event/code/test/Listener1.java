package Spring.Event.code.test;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/4/23 13:29
 * @description: 用来监听事件
 */
@Component
public class Listener1 {

    @EventListener
    public void sayHello(Event notifyEvent){
        System.out.println("微信:"+notifyEvent.getSource().toString());
        System.out.println("监听1号");
        SayHi sayHi =new SayHi();
        sayHi.say();
    }
}