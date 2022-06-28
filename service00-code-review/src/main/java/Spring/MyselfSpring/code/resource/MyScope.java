package Spring.MyselfSpring.code.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: krest
 * @date: 2021/4/25 12:25
 * @description: 定义Bean的作用域 Singleton 或者 prototype
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyScope {
    String value();
}
