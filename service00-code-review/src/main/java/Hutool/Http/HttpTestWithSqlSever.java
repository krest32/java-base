package Hutool.Http;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class HttpTestWithSqlSever {
    public static void main(String[] args) {
        GetAll();
//        insert();
    }
    public static void GetAll(){
        String url = "http://localhost:8013/mybatisplus/user/getAll";
        //链式构建请求
        String result2 = HttpRequest.get(url)
                .header(Header.USER_AGENT, "http")
                .timeout(20000)
                .execute().body();
        // 返回的异常，中文无法显示
        Console.log(result2);
    }

    /**
     * 通过body进行传递Json参数，用来存储数据
     */
    public static void insert(){
        String url = "http://localhost:8013/mybatisplus/user/insert";
        //链式构建请求
        String id = "1000";
        String name = "hahaha";
        JSONObject param = JSONUtil.createObj();
        param.put("id",id);
        param.put("name",name);
        String req = param.toString();
        System.out.println(req);
        String result2 = HttpRequest.post(url)
                .body(req)
                .execute().body();
        System.out.println(result2);
    }



}
