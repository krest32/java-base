package JavaSE.Xml;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.URL;

public class XmlUtils {
    /**
     * 从路径中加载xml文件，纯在报错的可能性
     */
    public static Document loadXML(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            // 读取XML文件,获得document对象
            document = saxReader.read(new File(filename));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    /**
     * (推荐)通过流的方式加载 Xml 成为 Document对象
     */
    public static Document loadXml(String filename) {
        Document document = null;
        try {
            String xmlString = FileHandleUtil.loadXml(filename);
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

    /**
     * 从网路路径中读取Xml文件
     */
    public static Document load(URL url) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            // 读取XML文件,获得document对象
            document = saxReader.read(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    /**
     * 将Document输出问xml文件
     */
    public static boolean doc2XmlFile(Document document, String filename) {
        boolean flag = true;
        try {
            XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        System.out.println(flag);
        return flag;
    }
}
