package JavaSE.Other;

/**
 * @author: krest
 * @date: 2021/6/2 12:08
 * @description: 短路与
 */
public class Other {
    public static void main(String[] args) {
        System.out.println(1==1 && 2==2);
        System.out.println(1==2 ? "正确":"error");
    }
}
