package com.krest.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krest.dom4j.entity.User;
import com.krest.dom4j.utils.FileHandleUtil;
import com.krest.dom4j.utils.JacksonUtil;
import com.krest.dom4j.utils.XmlUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;

public class demo {

    @Test
    public void tes1(){
        // 加载对象
        Document doc = XmlUtils.load("E:\\2.xml");
        // 获取根节点
        Element root = doc.getRootElement();
        // 追加节点
        Element menuElement = root.addElement("menu");

        Element engNameElement = menuElement.addElement("engName");

        engNameElement.setText("发动机");

        Element chiNameElement = menuElement.addElement("chiName");

        chiNameElement.setText("menu子节点");

        XmlUtils.doc2XmlFile(doc,"e:\\3.xml");
    }


    @Test
    public void createXMl(){
        String fileName = "e:/text.xml";
        Document document = DocumentHelper.createDocument();// 建立document对象，用来操作xml文件
        Element booksElement = document.addElement("books");// 建立根节点
        booksElement.addComment("This is a test for dom4j ");// 加入一行注释
        Element bookElement = booksElement.addElement("book");// 添加一个book节点
        bookElement.addAttribute("show", "yes");// 添加属性内容
        Element titleElement = bookElement.addElement("title");// 添加文本节点
        titleElement.setText("ajax in action");// 添加文本内容

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(fileName)));
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  modifyXMLFile(){
        String oldStr = "e:/text.xml";
        String newStr = "e:/text1.xml";
        Document document = null;
        //修改节点的属性
        try {
            SAXReader saxReader = new SAXReader(); // 用来读取xml文档
            document = saxReader.read(new File(oldStr)); // 读取xml文档
            List list = document.selectNodes("/books/book/@show");// 用xpath查找节点book的属性
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Attribute attribute = (Attribute) iter.next();
                if (attribute.getValue().equals("yes"))
                    attribute.setValue("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //修改节点的内容
        try {
            SAXReader saxReader = new SAXReader(); // 用来读取xml文档
            document = saxReader.read(new File(oldStr)); // 读取xml文档
            List list = document.selectNodes("/books/book/title");// 用xpath查找节点book的内容
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                element.setText("xxx");// 设置相应的内容
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(newStr)));
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void removeNode(){
        String oldStr = "e:/text.xml";
        String newStr = "e:/text1.xml";
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();// 用来读取xml文档
            document = saxReader.read(new File(oldStr));// 读取xml文档
            List list = document.selectNodes("/books/book");// 用xpath查找对象
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element bookElement = (Element) iter.next();
                // 创建迭代器，用来查找要删除的节点,迭代器相当于指针，指向book下所有的title节点
                Iterator iterator = bookElement.elementIterator("title");
                while (iterator.hasNext()) {
                    Element titleElement = (Element) iterator.next();
                    if (titleElement.getText().equals("ajax in action")) {
                        bookElement.remove(titleElement);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(newStr)));
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 加载Xml中的元素，然后转化生成Json数据
     */
    @Test
    public void  loadXml() throws JsonProcessingException {
        String filename = "e:\\2.xml";
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
        Map<String, Object> loadResult = new HashMap<String, Object>();
        User user = new User();
        user.setId("123");
        user.setName("杜鑫");
        loadResult.put("用户信息",user);

        // 从Xml中读取数据，赋值给到字符串
        Optional<Element> submitDateOpt = Optional.ofNullable((Element)document.selectSingleNode("/Application/Applicant/Applicant_Individual/i_first_thi_nme"));
        submitDateOpt.ifPresent(submitDate -> {
            String submitDateVal = submitDate.getText();
            if (StringUtils.isNotBlank(submitDateVal)) {
                loadResult.put("book_title", submitDateVal);
            }
        });
        // 将 map 集合转化为 Json
        String s = JacksonUtil.bean2Str(loadResult);
        System.out.println(s);

    }




}
