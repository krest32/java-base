package Spring.Aop.code;

/**
 * @author: krest
 * @date: 2021/4/25 10:16
 * @description: 业务逻辑程序
 */
public class Calculator {
    public int div(int i, int j) {
        System.out.println("+++++++++++++++++++++这主程序执行+++++++++++++++++++++++");
        return i / j;
    }
}
