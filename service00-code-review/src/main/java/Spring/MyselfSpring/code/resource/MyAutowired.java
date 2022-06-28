package Spring.MyselfSpring.code.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: krest
 * @date: 2021/4/25 13:24
 * @description: 自动装配注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD}) // 添加到方法的注解
public @interface MyAutowired {
}
