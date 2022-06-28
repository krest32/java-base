package JavaSE.Network.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Description：UDP server发送消息
 * @Author: Krest
 * @Date: 2021/9/4 23:18
 */
public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = null;
        DatagramPacket dp = null;

        // 构建发送对象
        ds = new DatagramSocket();
        // 将数据打包-->打成数据报
        String info = "hello world!  1234567890123456789";

        dp = new DatagramPacket(info.getBytes(),
                info.length(), InetAddress.getByName("localhost"),3333);
        // 发出数据报
        ds.send(dp);

    }
}
