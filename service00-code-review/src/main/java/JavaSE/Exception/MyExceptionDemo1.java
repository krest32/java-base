package JavaSE.Exception;

import JavaSE.Exception.entity.MyException;
import org.junit.Test;

/**
 * @Description：
 * @Author: Krest
 * @Date: 2021/9/4 23:07
 */
public class MyExceptionDemo1 {
    @Test
    public void test1(){
        try {
            // 调用异常代码
            checkScore(-1);
        } catch (Exception e) {
            // 输出错误
            // MyException: 分数只能在 0~120 之间
            System.out.println(e);
            // 分数只能在 0~120 之间
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    // 检查分数
    public static void checkScore(int score) throws MyException {
        if (score < 0 || score > 120) {
            throw new MyException("分数只能在 0~120 之间");
        } else {
            System.out.println("分数正常");
        }
    }

}
