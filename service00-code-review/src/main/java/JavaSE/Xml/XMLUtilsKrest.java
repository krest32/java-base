package JavaSE.Xml;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class XMLUtilsKrest {

    public static Document getDoc(String fileName){

        Document document = null;
        try {
            String xmlString = FileHandleUtil.loadXml(fileName);
            System.out.println(xmlString);
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding("utf-8");
            // 读取XML文件,获得document对象
            InputStream is = new ByteArrayInputStream(xmlString.getBytes("utf-8"));
            document = saxReader.read(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }
}
