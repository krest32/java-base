package Hutool.Http;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HttpPostWithFromDocumentSimplification {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(new File("E:\\Document_Simplification\\testDoc.xml"));
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        fis.close();
        String xml = new String(bs, "UTF-8");

        String url = "http://localhost:8888/api/v1/documentService";
        //链式构建请求
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "http")
                .form("sXML",xml)
                .timeout(20000)
                .execute().body();

        // 返回的异常，中文无法显示
        System.out.println(result2);
    }

}
