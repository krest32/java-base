package JavaSE.IO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo {
    public static void main(String[] args) {
        String path = "D:\\fileList.txt";
//        NioReadFile(path);
        BioReadFile(path);
    }

    public static void NioReadFile(String path) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(path, "rw");
            // 获取得到 channel
            FileChannel fileChannel = aFile.getChannel();
            // 设置缓存区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // channel 从缓存去读取数据
            int bytesRead = fileChannel.read(buf);

            System.out.println(bytesRead);
            // 开始逐行获取文件中的内容
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void BioReadFile(String filePath) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buf = new byte[1024];
            // 字节缓存区
            int bytesRead = in.read(buf);
            // 将数据读读出
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
