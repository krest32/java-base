package JavaSE.Network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        //InetAdress类表示IP地址
        //获取本机IP
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
        //获得主机名
        System.out.println(ip.getHostName());
        //获得IP地址
        System.out.println(ip.getHostAddress());
    }
}
