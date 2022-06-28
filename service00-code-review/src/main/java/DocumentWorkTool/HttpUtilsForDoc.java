package DocumentWorkTool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilsForDoc {

    /**
     *  请求示例：发送Get-Form请求，携带多个参数
     */
    private static void TestSendGetWithFormMultiparam() throws Exception {
        String url = "http://localhost:8001/EE/hello6";
        String params = "name=杜鑫&age=16";
        String s = SendGetForm(url,params);
        System.out.println(s);
    }

    /**
     *  请求示例：发送Get-Form请求,携带单个参数
     */
    private static void TestSendGetWithForm() throws Exception {
        String url = "http://localhost:8001/EE/hello3";
        String params = "name=杜鑫";
        String s = SendGetForm(url,params);
        System.out.println(s);
    }

    /**
     * 请求示例：发送Get请求，无参数
     */
    private static void TestSendGet() throws Exception {
        String url = "http://localhost:8001/EE/hello5";
        String s = SendGetWithoutParams(url);
        System.out.println(s);
    }

    /**
     * 请求示例：测试使用Post-Json方式发送无参数请求
     */
    private static void PostWithOutParam(){
        String uri="http://localhost:8001/EE/testPostWithParam";
        String charset="UTF-8";

        //请求体设置
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("token","ASDF304IXK2WCQVBM21WN4F35OU6QV0");
        headerMap.put("Content-Type","application/json");
        headerMap.put("accept", "*/*");
        headerMap.put("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        String res = sendPost(uri,charset);
        System.out.println(res);
    }

    /**
     *  请求示例：测试使用Post-Form方式发送请求
     */
    private static void DocumentSimplificationTest () throws IOException {
        FileInputStream fis = new FileInputStream(new File("E:\\testDoc.xml"));
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        fis.close();
        String xml = new String(bs, "UTF-8");
        String paramXml = "sXML="+xml;
        String uri = "http://localhost:8888/api/v1/documentService";
        String charset="UTF-8";

        // 设置请求头
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/x-www-form-urlencoded");
        headerMap.put("connection", "Keep-Alive");
        headerMap.put("accept", "*/*");
        headerMap.put("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        String res = sendPostForm(uri, charset, paramXml, headerMap);
        System.out.println(res);
    }

    /**
     *  无参数post
     */
    public static String sendPost(String uri, String charset) {
        String result = null;
        InputStream in = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("POST");
            urlcon.connect();// 获取连接
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer bs = new StringBuffer();
            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
            result = bs.toString();
        } catch (Exception e) {
            System.out.println("[请求异常][地址：" + uri + "][错误信息：" + e.getMessage()+ "]");
        } finally {
            try {
                if (null != in){
                    in.close();
                }
            } catch (Exception e2) {
                System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");
            }
        }
        return result;
    }


    /**
     * Post 发送 Form 请求
     */
    public static String sendPostForm(String uri, String charset,String params, Map<String,String> headerMap) {
        String result = null;
        InputStream in = null;
        OutputStreamWriter out = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            // Post请求设置参数
            urlcon.setDoInput(true);
            urlcon.setDoOutput(true);

            urlcon.setRequestMethod("POST");
            if (!headerMap.isEmpty()){
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    urlcon.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }
            // 获取连接
            urlcon.connect();

            // 发送请求
            out = new OutputStreamWriter(urlcon.getOutputStream(), "UTF-8");
            out.write(params);
            out.flush();

            // 获取返回数据
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer bs = new StringBuffer();
            String line = null;

            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
            result = bs.toString();

        } catch (Exception e) {
            System.out.println("[请求异常][地址：" + uri + "][错误信息："+ e.getMessage() + "]");
        } finally {
            try {
                if (null != in){
                    in.close();
                }
                if (null != out){
                    out.close();
                }
            } catch (Exception e2) {
                System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");
            }
        }
        return result;
    }

    /**
     * 发送Get请求，无参数
     */
    public static String SendGetWithoutParams(String urlParam ) {

        URLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            con = url.openConnection();

            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应流
            InputStream inputStream = con.getInputStream();
            //将响应流转换成字符串
            resultBuffer = new StringBuffer();

            String line;

            buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));

            while ((line = buffer.readLine()) != null) {
                resultBuffer.append(line);
            }
            return resultBuffer.toString();

        }catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 发送Get请求，发送Form参数
     */
    public static String SendGetForm(String urlParam,String resForm ) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();

        try {
            URL realUrl = new URL(urlParam);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 发送POST请求必须设置如下两行   否则会抛异常（java.net.ProtocolException: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)）
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流并开始发送参数
            out = new PrintWriter(conn.getOutputStream());
            //下面开始设置参数，下面是随便模拟的两个参数sign和name
            out.print(resForm);
            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
