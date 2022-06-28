package JavaSE.IO;

import JavaSE.IO.entity.Member;
import org.junit.Test;

import java.io.*;

/**
 * @Description：将文件中存储的对象信息打印出来
 * @Author: Krest
 * @Date: 2021/9/4 22:04
 */
public class DataStreamDemo1 {
    @Test
    public void test(){
    Member[] members = {
                    new Member("Justin",90),
                    new Member("momor",95),
                    new Member("Bush",88)
                };
        try {
            // 将对象信息写入到文件当中
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("D:/data/tiger.jpg"));
            for(Member member:members) {
                //写入UTF字符串
                dataOutputStream.writeUTF(member.getName());
                //写入int数据
                dataOutputStream.writeInt(member.getAge());
            }
            //所有数据至目的地
            dataOutputStream.flush();
            //关闭流
            dataOutputStream.close();

            // 将文件中的信息转化出来
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D:/data/tiger.jpg"));

            //读出数据并还原为对象
            for(int i=0;i<members.length;i++) {
                //读出UTF字符串
                String name =dataInputStream.readUTF();
                //读出int数据
                int score =dataInputStream.readInt();
                //赋值给到对象
                members[i] = new Member(name,score);
            }

            //关闭流
            dataInputStream.close();
            //显示还原后的数据
            for(Member member : members) {
                System.out.printf("%s\t%d%n",member.getName(),member.getAge());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
