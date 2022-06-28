package Spring.MyselfSpring.code.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: krest
 * @date: 2021/4/25 11:24
 * @description: 定义Bean主键
 */
@Retention(RetentionPolicy.RUNTIME) //运行加载改注解
@Target({ElementType.TYPE}) // 注解使用位置
public @interface MyComponent {
    //设定注解的属性，默认值可以为空
    String value() default "";
}
