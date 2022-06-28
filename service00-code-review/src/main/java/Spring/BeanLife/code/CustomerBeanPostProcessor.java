package Spring.BeanLife.code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * @author: krest
 * @date: 2021/4/25 08:52
 * @description: 继承BeanPostProcessor，实现对Bean初始化前后的后置处理，主要针对Bean的初始化进行操作
 */

public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【步骤10】执行BeanPostProcessor中postProcessBeforeInitialization方法,beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【步骤14】执行BeanPostProcessor的postProcessAfterInitialization方法,beanName=" + beanName+
                "；如果有Aop，那么同时执行AOP功能，最终对象为代理对象");
        return bean;
    }
}
