package Spring.Event.code.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: krest
 * @date: 2021/4/23 14:15
 * @description:
 */

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
    public void publishEvent(Object source) {
        ctx.publishEvent(new Event(source));
    }
}
