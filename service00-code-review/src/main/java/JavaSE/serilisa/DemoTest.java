package JavaSE.serilisa;

import java.io.IOException;

public class DemoTest {
    public static void main(String[] args) throws IOException {
        DemoBean.aaa.put("haha","haha");
        System.out.println(Serializer.serialize(DemoBean.aaa).length);
    }
}
