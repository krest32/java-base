package JavaSE.Basic;

import org.junit.Test;

/**
 * @Description：基本数据包装类测试
 * @Author: Krest
 * @Date: 2021/9/4 11:06
 */
public class PackagingClassDemo1 {

    /**
     * Integer 缓冲区超过127
     */
    @Test
    public void test(){
        Integer i5=127;
        Integer i6=127;
        System.out.println(i5==i6);
        System.out.println(i5.equals(i6));
        Integer i7=128;
        Integer i8=128;
        System.out.println(i7==i8);
        System.out.println(i7.equals(i8));
    }
}
