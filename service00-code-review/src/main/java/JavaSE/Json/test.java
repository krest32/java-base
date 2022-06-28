package JavaSE.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("456");

        // list转化Json
        System.out.println(JacksonUtil.bean2Str(list));

        Map<String,Object> map = new HashMap<>();
        map.put("name","haha");
        map.put("age",16);

        // map转化json
        System.out.println(JacksonUtil.bean2Str(map));

        User user = new User();
        user.setName("duxin");
        user.setAge(12);
        // 对象转化json
        String userStr = JacksonUtil.bean2Str(user);
        System.out.println(userStr);

        // json转化对象
        User user1 = JacksonUtil.str2Bean(User.class, userStr);
        System.out.println(user1);


        // json 转化 LinkedHashMap
        Map<String,Object> uMap = (Map)JacksonUtil.str2Bean(userStr);

        System.out.println(uMap);


    }

}
