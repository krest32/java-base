package DocumentWorkTool;



import java.io.*;
import java.util.HashMap;


public class Document_Simplification_WorkTool {

    public static void main(String[] args) throws IOException, InterruptedException {
        long  start = System.currentTimeMillis();
        // 列表文件位置
        String fileListFile="e:\\Document_Simplification\\DocFileList.txt";
        // xml文件所在目录路径
        String xmlFilePath="e:\\Document_Simplification\\";
        // Post 请求 Api 地址
        String url = "http://localhost:8888/api/v1/documentService";


        FileReader in = new FileReader(new File(fileListFile));
        LineNumberReader reader = new LineNumberReader(in);
        String fileName = reader.readLine();
        int lines = 0;

        // 执行主程序
        while (fileName != null) {
            lines++;
            String xmlFile = xmlFilePath+fileName+".xml";
            System.out.println("开始执行第"+lines+"个文件："+xmlFile);
            DocMainRun(xmlFile,url);
            fileName = reader.readLine();
        }

        reader.close();
        in.close();

        long end = System.currentTimeMillis();
        System.out.println("执行完毕，文件数量共计："+lines+"个,共计耗时："+(end-start)/1000+"s");

    }

    /**
     * 使用Xml的方式进行提交
     */
    public static void DocMainRun(String file, String url) throws IOException {

        String uri = url;

        // 读取xml文件内容
        FileInputStream fis = new FileInputStream(new File(file));
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        fis.close();
        String xml = new String(bs, "UTF-8");
        String paramXml = xml;
        String charset="UTF-8";

        // 设置请求头
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/xml");
        headerMap.put("connection", "Keep-Alive");
        headerMap.put("accept", "*/*");
        headerMap.put("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        // 返回执行结果
        String res = HttpUtilsForDoc.sendPostForm(uri, charset, paramXml, headerMap);

        // 打印输出结果
        System.out.println("执行结果"+ " -> "+res);
    }

}
