package Hutool.Http;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

public class HttpPostWithPathParam {
    public static void main(String[] args) {
        String name = "杜鑫";
        String url ="http://localhost:8001/EE/hello4/"+name;
        //链式构建请求
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "http")
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);
    }
}
