package JavaSE.Exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: krest
 * @date: 2021/6/9 00:06
 * @description: 堆溢出测试
 */
public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i=0;
        while(true){
            list.add(new byte[5*1024*1024]);
            System.out.println("分配次数："+(++i));
        }
    }

}
