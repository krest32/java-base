package DesignMode.code.singletonPattern;

/**
 * @author: krest
 * @date: 2021/4/25 17:37
 * @description: 枚举
 */
public enum EnumSingleton {
    INSTANCE;
    public void test(){
        System.out.println("单例模式");
    }
}
