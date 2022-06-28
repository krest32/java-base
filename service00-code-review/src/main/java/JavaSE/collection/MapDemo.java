package JavaSE.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    @Test
    public void MapShow(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,null);
        map.put(2,null);
        System.out.println(map.size());
    }

    @Test
    public void safeMap(){
        Map<Integer,Integer> map = new HashMap<>();
        Map<Integer, Integer> safeMap = Collections.synchronizedMap(map);
    }

}
