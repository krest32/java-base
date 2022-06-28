package JavaSE.Json.JsonToXml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


/**
 * Xml将对象转化为字符串
 */
public class XmlUtils {

    public static void main(String[] args) {
        User user = new User("duxin",23);

    }


    /**
     * 实体类必须被 @XmlRootElement 注释，否则无法进行转化
     */
    public static String JavaBeanToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sw.toString();
    }


}
