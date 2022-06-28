package Spring.MyselfSpring.code.src;

import Spring.MyselfSpring.code.resource.*;

/**
 * @author: krest
 * @date: 2021/4/25 11:28
 * @description: 测试Bean文件
 */
@MyComponent("rootService")
@MyScope("prototype")
public class RootService implements MyBeanNameWare , MyInitializingBean {

    @MyAutowired
    private OrderService orderService;

    public String getBeanName() {
        return beanName;
    }

    private String beanName;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void  testAutowired(){
        System.out.println("orderService的属性："+orderService);
    }

    @Override
    public void setBeanName(String var1) {
        beanName = var1;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化属性注入测试");
    }

}
