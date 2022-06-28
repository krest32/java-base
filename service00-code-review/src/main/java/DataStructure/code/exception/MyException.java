package DataStructure.code.exception;

/**
 * @author: krest
 * @date: 2021/1/2 23:31
 * @description: 自定义异常
 */
public class MyException extends RuntimeException{

    /**
     * 无参的构造方法
     */
    public MyException() {
    }

    /**
     * 传递异常信息的构造方法,然后直接调用进行传入参数即可
     * @param message 异常的信息
     */
    public MyException(String message) {
        super(message);
    }
}
