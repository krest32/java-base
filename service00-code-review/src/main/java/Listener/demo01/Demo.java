package Listener.demo01;

public class Demo {

    public static void main(String[] args) {
        Student test = new Student();

        test.addPropertyChangeListener(new BeanTestListener());
        test.setName("dirk.zhang");
        test.setName("dirk.zhangzhang");
    }
}
