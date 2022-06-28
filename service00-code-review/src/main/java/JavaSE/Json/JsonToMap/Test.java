package JavaSE.Json.JsonToMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws JsonProcessingException {
        String str = JsonString.JsonString();
        Map<String,Object> map = JsonToMap(str);
        System.out.println(map);
    }

    public static Map<String,Object> JsonToMap(String str) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        // 将Json转换为Map
        map = mapper.readValue(str, new TypeReference<HashMap<String, Object>>(){});
        return map;
    }
}
