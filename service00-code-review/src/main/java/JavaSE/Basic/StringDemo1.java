package JavaSE.Basic;

import org.junit.Test;

/**
 * @Description：String 常用 API
 * @Author: Krest
 * @Date: 2021/9/4 11:12
 */
public class StringDemo1 {
    @Test
    public void test1(){
        //无参构造
        String str1 = new String();
        System.out.println(str1);
        //char数组构造
        char[] ch = {'A','B'};
        String str2 = new String(ch);
        System.out.println(str2);
        //byte[]
        byte[] byteArray = {97, 98, 99 };
        String str3 = new String(byteArray);
        System.out.println(str3);

    }

    @Test
    public void test2(){
        //直接创建
        String str4 = "Hello";
        System.out.println(str4);

        String str1 = "abc";
        //推荐写法
        System.out.println("abc".equals(str1)) ;
        //不推荐写法，如果str1 = null，就会报空指针异常错误
        System.out.println(str1.equals("abc"));

        String str2 = "ABC";
        System.out.println(str1.equals(str2));
        System.out.println(str1.equalsIgnoreCase(str2));
    }

    @Test
    public void test3(){
        int length = "lllsejinjfjsiuvfanhaui".length();
        System.out.println(length);

        String str1 = "hello";
        String str2 = "world";
        String str3 = str1.concat(str2);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        char ch = str1.charAt(4);
        System.out.println(ch);

        int index = str1.indexOf("ll");
        System.out.println(index);
    }

    @Test
    public void test4(){

        //字符串替换为char[]
        char[] chars = "hello".toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);

        }
        //字符串替换为byte[]
        byte[] bytes = "Abc0".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
        //旧字符串替换为新字符串
        String str1 = "我去你大爷的！我去你大爷的！我去你大爷的！我去你大爷的！";
        String str2 = str1.replace("大爷", "**");
        System.out.println(str2);

    }

    @Test
    public void test5(){
        String str1 = "aaa,bbb,ccc";
        String[] str = str1.split(",");
        for (String s : str) {
            System.out.println(s);
        }

        String str2 = "aaa bbb ccc";
        String[] array = str2.split(" ");
        for (String s : array) {
            System.out.println(s);
        }
        
        //.需要用正则表达式表示
        String str3 = "aaa.bbb.ccc";
        String[] split = str3.split("\\.");
        for (String s : split) {
            System.out.println(s);
        }

    }
}
