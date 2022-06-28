package Yml;

import org.junit.Assert;
import org.yaml.snakeyaml.Yaml;

public class Test {
    public static void main(String[] args) {
//        System.out.println(YmlUtils.configMap);
//        System.out.println(YmlUtils.getConfigStr("a.b.c"));
//        System.out.println(YmlUtils.getConfigStr("a.b.d"));
//        System.out.println(YmlUtils.getConfigStr("a.b.e"));
//        System.out.println(YmlUtils.getConfigObj("a.b"));
//        System.out.println(YmlUtils.getConfigArr("a.b.f"));
//        System.out.println(YmlUtils.getConfigArr("companies"));

//
//        YmlUtils.setYmlFile("address.yaml");
//        System.out.println(YmlUtils.configMap);


        Yaml yaml = new Yaml();
        Address ret = yaml.loadAs(yaml.getClass().getClassLoader()
                .getResourceAsStream("address.yaml"), Address.class);
        Assert.assertNotNull(ret);
        Assert.assertEquals("MI", ret.getState());
        System.out.println(ret.getCity());
        System.out.println(ret.getLines());
        System.out.println(ret.getPostal());
        System.out.println(ret.getState());

    }
}
