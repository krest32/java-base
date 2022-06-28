package com.krest.xml.service.impl;

import com.krest.Service.Response.R;
import com.krest.xml.entity.DocumentTest;
import com.krest.xml.service.XMLService;
import com.krest.xml.utils.OrderNumberCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author: krest
 * @date: 2021/6/28 11:58
 * @description:
 */
@Slf4j
@Service
public class XMLServiceImpl implements XMLService {

    public static final String BOM_CALLTYPE = "calltype";
    public static final String BOM_INTEGRATION_CAP_BLAZE_SYNC = "Cap_Blaze_Sync";


    @Override
    public R analysisXML(String res) throws TransformerException {

        // 判断res是否有输入,是否合法
        if(StringUtils.isEmpty(res) || res.equals("")){
            return R.error().code(401).message("请求信息为空");
        }

        // 将字符串转化为对象进行操作，从而对xml文件进行操作,获取指定标签的内容
        DocumentBuilder documentBuilder = null;
        Document doc = null;
        String App_Name = "";
        String CLass_Number = "";
        try {
            documentBuilder = newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(res.getBytes("UTF-8"));
            doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    String nodeName = element.getNodeName();
                    String nodeContent = element.getTextContent();
                    //log.info(nodeName + ":" + nodeContent);
                    if(nodeName!=null && nodeName.equals("App_Name"))
                    {
                        App_Name = nodeContent;
                    }
                    if(nodeName!=null && nodeName.equals("CLass_Number"))
                    {
                        CLass_Number = nodeContent;
                    }
                }
            }
            System.out.println(App_Name+""+CLass_Number);

            // 追加新的节点
            Element rootElement = doc.getDocumentElement();
            Node node=doc.createElement("new-Node");
            node.setTextContent("this-is-a-new node");
            //追加子节点
            rootElement.appendChild(node);
            try {
                stream.close();
            } catch (Exception ex) {
                log.error(ex.toString(), ex);
                return R.error().code(500) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将Xml对象转化问文件形式
        // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
        doc.setXmlStandalone(true);
        // 开始生成XML文件
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        //StreamResult result = new StreamResult(writer);
        // 生成到文件里
        StreamResult result = new StreamResult("D:\\cds.xml");
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); // .replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return R.ok().data("txt",doc);
    }


    // 将XMl字符串，转化为XML对象进行操作
    public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory","com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        log.info("To set security XML.");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);
        log.info("finish security XML setting!");
        return documentBuilderFactory.newDocumentBuilder();
    }
}
