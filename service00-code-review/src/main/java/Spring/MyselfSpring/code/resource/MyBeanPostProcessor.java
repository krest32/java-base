package Spring.MyselfSpring.code.resource;

/**
 * @author: krest
 * @date: 2021/4/25 13:57
 * @description:
 */
public interface MyBeanPostProcessor {

     Object postProcessBeforeInitialization(Object bean, String beanName);

     Object postProcessAfterInitialization(Object bean, String beanName);

}
