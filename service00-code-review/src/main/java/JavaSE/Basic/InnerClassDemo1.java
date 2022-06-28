package JavaSE.Basic;

import org.junit.Test;

/**
 * @Description：内部类Demo,四种不同的内部类
 * @Author: Krest
 * @Date: 2021/9/4 11:57
 */
public class InnerClassDemo1 {

    static class StaticInner {
        public void visit() {
            System.out.println("静态内部类");
        }
    }

    class Inner {
        public void visit() {
            System.out.println("成员内部类");
        }
    }

    @Test
    public void test1(){
        System.out.println("执行测试程序");
        InnerClassDemo1.StaticInner staticInner = new InnerClassDemo1.StaticInner();
        staticInner.visit();

        InnerClassDemo1 innerClassDemo = new InnerClassDemo1();
        InnerClassDemo1.Inner inner = innerClassDemo.new Inner();
        inner.visit();

        new Service() {
            @Override
            public void method() {
                    System.out.println("匿名内部类" );
            }
        }.method();

        class Inner2 {
            private void visit(){
                System.out.println("局部内部类");
            }
        }
        Inner2 inner2 = new Inner2();
        inner2.visit();


    }

}
