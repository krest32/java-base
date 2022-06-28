package JavaSE.Network.TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * TCP 服务端
 */
public class TCPServer {

    public static void main(String[] args) throws IOException {
        //服务器开启了一个监听端口
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("开启了服务器");
        while (true){
            //等待客户端连接
            Socket accept = serverSocket.accept();
            System.out.println("有一个人连接过来了");
            //获得客户端输入的东西
            InputStream inputStream = accept.getInputStream();
            //包装成DataInputStream流
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            //通过流流读取消息
            String s = dataInputStream.readUTF();
            System.out.println(s);
        }
    }
}
