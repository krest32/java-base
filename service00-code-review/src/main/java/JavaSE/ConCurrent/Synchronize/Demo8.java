package JavaSE.ConCurrent.Synchronize;

/**
 * Description：锁消除
 *              消除StringBuffer同步锁
 *              消除锁是虚拟机另外一种锁的优化，这种优化更彻底
 *              通过对运行上下文的扫描，去除不可能存在共享资源竞争的锁，
 *              通过这种方式消除没有必要的锁，可以节省毫无意义的请求锁时间，
 *              如下StringBuffer的append是一个同步方法，
 *              但是在add方法中的StringBuffer属于一个局部变量，
 *              并且不会被其他线程所使用，因此StringBuffer不可能存
 *              在共享资源竞争的情景，JVM会自动将其锁消除。

 * Author: Krest
 * Date: 2021/9/5 11:52
 */
public class Demo8 {

    public void add(String str1, String str2) {
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }

    public static void main(String[] args) {
        Demo8 rmsync = new Demo8();
        for (int i = 0; i < 10000000; i++) {
            rmsync.add("abc", "123");
        }
    }
}
