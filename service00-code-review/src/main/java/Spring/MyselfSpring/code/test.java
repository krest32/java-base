package Spring.MyselfSpring.code;

import Spring.MyselfSpring.code.resource.KrestApplicationContext;
import Spring.MyselfSpring.code.src.AppConfig;
import Spring.MyselfSpring.code.src.RootService;
import Spring.MyselfSpring.code.src.UserService;
import org.junit.Test;

/**
 * @author: krest
 * @date: 2021/4/25 11:17
 * @description: 测试程序入口
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException {
        KrestApplicationContext applicationContext = new KrestApplicationContext(AppConfig.class);

        // 创建多利Bean
//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));
//
//        // 属性注入测试
        UserService userService = (UserService) applicationContext.getBean("userService");
//        userService.test();
//
//
//
//        // 属性注入测试
//        RootService rootService = (RootService) applicationContext.getBean("rootService");
//        System.out.println("root:"+rootService.getBeanName());
    }
}
