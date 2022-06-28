package JavaSE.Network;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * InetSocketAddress类
 * 说到端口，则要引入一个类：InetSocketAddress
 * 此类实现 IP 套接字地址（IP 地址 + 端口号）。
 * 构造方法摘要
 * InetSocketAddress(InetAddress addr, int port)
 * 根据 IP 地址和端口号创建套接字地址。
 * InetSocketAddress(int port)
 * 创建套接字地址，其中 IP 地址为通配符地址，端口号为指定值。
 * InetSocketAddress(String hostname, int port)
 * 根据主机名和端口号创建套接字地址。
 * 常用方法摘要
 * InetAddress getAddress()
 * 获取 InetAddress。
 * String getHostName()
 * 获取 hostname。
 * int getPort()
 * 获取端口号。
 */
public class IPDemo3 {

    public static void main(String[] args) {

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8082);

        System.out.println(inetSocketAddress.getHostName());
        //获得InetSocketAddress的端口
        System.out.println(inetSocketAddress.getPort());

        System.out.println(inetSocketAddress.getHostString());
        //返回一个InetAddress对象（IP对象）
        InetAddress address = inetSocketAddress.getAddress();
    }
}
