package JVM.GC;

/**
 * @author: krest
 * @date: 2021/9/5 19:13
 * @description:
 */
public class Demo1 {
    public static void main(String[] args) {
        Demo1 test = new Demo1();
        test = null;
        System.gc(); // 手动回收垃圾
    }

    @Override
    protected void finalize() throws Throwable {
        // gc回收垃圾之前调用
        System.out.println("gc回收垃圾之前调用的方法");
    }
}
