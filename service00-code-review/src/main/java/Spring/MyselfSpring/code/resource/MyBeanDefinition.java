package Spring.MyselfSpring.code.resource;

/**
 * @author: krest
 * @date: 2021/4/25 12:35
 * @description: 用来存放Bean的属性信息
 */
public class MyBeanDefinition {
    private Class clazz;
    private String scope;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
