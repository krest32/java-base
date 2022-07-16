package JavaSE.serilisa;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class DemoBean implements Serializable {
    private static final long serialVersionUID = 1;
    static ConcurrentHashMap<String,String> aaa = new ConcurrentHashMap<>();
}
