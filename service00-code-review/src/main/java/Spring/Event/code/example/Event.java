package Spring.Event.code.example;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author: krest
 * @date: 2021/4/23 13:18
 * @description: 自定义事件
 */
public class Event extends ApplicationEvent {

    @Getter
    private String msg;

    public Event(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}