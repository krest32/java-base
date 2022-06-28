package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.krest.Service.Response.R;
import com.krest.es.entity.User;
import com.krest.es.service.EsService;
import com.krest.es.service07_es;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author: krest
 * @date: 2021/5/23 18:39
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = service07_es.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class main {

    @Autowired
    private EsService esService;


    @Test
    public void test() throws ExecutionException, InterruptedException {
        R userById = esService.getUserById("2");
        Object  object= userById.getData().get("user");
        User user = JSON.parseObject(JSON.toJSONString(object), new TypeReference<User>() { });
        System.out.println(user);
    }
}
