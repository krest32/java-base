package JavaSE.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description：InputStream
 * @Author: Krest
 * @Date: 2021/9/4 19:00
 */
public class InputStreamDemo1 {

    @Test
    public void test1(){
        //统计文件字节长度
        int count=0;
        //文件输入流
        InputStream streamReader = null;
        try{
            streamReader=new FileInputStream(new File("D:/data/tiger.jpg"));
            /*1.new File()里面的文件地址也可以写成前一个\是用来对后一个
             * 进行转换的，FileInputStream是有缓冲区的，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。
             */
            while(streamReader.read()!=-1) {
                count++;
            }
            System.out.println("---长度是： "+count+" 字节");
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                streamReader.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
