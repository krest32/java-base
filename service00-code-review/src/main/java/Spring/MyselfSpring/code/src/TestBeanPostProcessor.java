package Spring.MyselfSpring.code.src;

import Spring.MyselfSpring.code.resource.MyBeanPostProcessor;
import Spring.MyselfSpring.code.resource.MyComponent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: krest
 * @date: 2021/4/25 14:00
 * @description: 所有创建的Bean都会执行这个方法
 */
@MyComponent("TestBeanPostProcessor")
public class TestBeanPostProcessor implements MyBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(beanName.equals("userService")){
            ((UserServiceImpl)bean).setName("你好帅");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后");
        if (beanName.equals("userService")){
            Object proxtyInstance = Proxy.newProxyInstance(TestBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("==========执行代理逻辑=================");
                    return  method.invoke(bean,args);
                }
            });
            return proxtyInstance;
        }
        return bean;
    }
}
