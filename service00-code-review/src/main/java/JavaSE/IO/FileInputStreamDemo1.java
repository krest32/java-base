package JavaSE.IO;

import org.junit.Test;

import java.io.*;

/**
 * @Description： FileInputStream 例子
 * @Author: Krest
 * @Date: 2021/9/4 19:08
 */
public class FileInputStreamDemo1 {
    /**
     * 复制文件
     */
    @Test
    public void test1(){
        //一次取出的字节数大小,缓冲区大小
        byte[] buffer=new byte[512];
        int numberRead=0;
        FileInputStream input=null;
        FileOutputStream out =null;
        try {
            input=new FileInputStream("D:/data/tiger.jpg");
            //如果文件不存在会自动创建
            out=new FileOutputStream("D:/data/tiger2.jpg");

            //numberRead的目的在于防止最后一次读取的字节小于buffer长度，
            while ((numberRead=input.read(buffer))!=-1) {
                //否则会自动被填充0
                out.write(buffer, 0, numberRead);
            }
        } catch (final IOException e) {
            // TODO自动生成的 catch 块
            e.printStackTrace();
        }finally{
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                // TODO自动生成的 catch 块
                e.printStackTrace();
            }

        }
    }

    /**
     * 按行读取文件的内容
     */
    @Test
    public void ReadLine() throws IOException {
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("d://data//test.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        //close
        inputStream.close();
        bufferedReader.close();


    }

}
