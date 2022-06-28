package JavaSE.Date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: krest
 * @date: 2021/6/25 17:24
 * @description: 日期时间格式化
 */
public class DateFormat {
    @Test
    public void test1() {
        java.text.DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }

    @Test
    public void test2() {
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }

    @Test
    public void test3() {
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }

    @Test
    public void test4() {
        java.text.DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }

    @Test
    public void test5() {
        java.text.DateFormat df = new SimpleDateFormat("HH");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }

    @Test
    public void test6() {
        java.text.DateFormat df = new SimpleDateFormat("dd");
        Date d1 = new Date();
        String format = df.format(d1);
        System.out.println(format);
    }
}
