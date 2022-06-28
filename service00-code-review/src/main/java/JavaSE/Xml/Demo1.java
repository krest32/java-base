package JavaSE.Xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import java.util.List;

public class Demo1 {
    @Test
    public void demo1(){
        Document document = XmlUtils.loadXML("D:\\Project\\java\\data-structure-learning\\Code-Review\\src\\main\\resources\\1.xml");
        Element rootElement = document.getRootElement();
        List<Node> elements = rootElement.elements();
        for (Node element : elements) {
            System.out.println(element.getName());
        }

    }
}
