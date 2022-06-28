package JVM.Exception;

/**
 * @author: krest
 * @date: 2021/6/9 00:07
 * @description: 栈溢出测试（使用递归进行测试）
 */
public class StackOutOFMemeoryDemo {

    int depth = 0;

    public void sofMethod(){
        depth ++ ;
        sofMethod();
    }

    public static void main(String[] args) {
        StackOutOFMemeoryDemo test = null;
        try {
            test = new StackOutOFMemeoryDemo();
            test.sofMethod();
        } finally {
            System.out.println("递归次数："+test.depth);
        }
    }
}
