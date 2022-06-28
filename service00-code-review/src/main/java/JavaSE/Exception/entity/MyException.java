package JavaSE.Exception.entity;

/**
 * @Description：
 * @Author: Krest
 * @Date: 2021/9/4 23:06
 */
public class MyException extends Exception {
    // 无参构造
    public MyException() {}
    // 带参构造
    public MyException(String message) {
        // 异常错误消息
        super(message);
    }

}
