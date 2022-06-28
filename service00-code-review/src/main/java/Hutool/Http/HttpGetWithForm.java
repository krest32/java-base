package Hutool.Http;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;


/**
 * 表单提交方式
 */
public class HttpGetWithForm {
    public static void main(String[] args) {
        String name = "杜鑫";
        String url ="http://localhost:8001/EE/hello2";
        //链式构建请求
        String result2 = HttpRequest.get(url)
                .header(Header.USER_AGENT, "http")
                .form("name",name)
                //超时，毫秒
                .timeout(20000)
                .execute().body();

        Console.log(result2);
    }
}
