package Spring.Event.code.test;

import org.springframework.context.ApplicationEvent;

/**
 * @author: krest
 * @date: 2021/4/23 13:18
 * @description: 自定义事件
 */
public class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }
}