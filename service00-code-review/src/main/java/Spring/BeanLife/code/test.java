package Spring.BeanLife.code;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: krest
 * @date: 2021/4/25 08:52
 * @description:
 */
public class test {
    public static void main(String[] args) {
        // 通过Xml的方式向Spring容器中添加一个自定义的B而安文件
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Spring容器初始化完毕========================");

        System.out.println("从容器中获取Bean");
        IocBeanLifeService service = context.getBean("iocBeanLifeService", IocBeanLifeService.class);

        System.out.println(service.toString());

        System.out.println("Spring容器准备关闭==========================");
        context.close();
        System.out.println("Spring容器完成关闭===========================");
    }
}
