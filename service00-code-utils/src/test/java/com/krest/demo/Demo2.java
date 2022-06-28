package com.krest.demo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.krest.utils.util.FileUtilsKrest;
import com.krest.utils.util.StringUtilsKrest;
import com.krest.utils.util.WatchTool;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
public class Demo2 {

    public static void main(String[] args) {
        WatchTool.start();
        FileUtilsKrest fileUtil = new FileUtilsKrest();
        String file = "d:\\1.txt";
        for (int i = 0; i < 10000; i++) {
            fileUtil.writeStr(file,"aaaa",true);
        }
        System.out.println(WatchTool.stop());
    }

    @Test
    public void test(){
        String jsonFilePath = "F:\\project\\java\\data-structure-learning\\service00-code-utils\\src\\main\\resources\\json";

        FileUtilsKrest fileUtilsKrest  = new FileUtilsKrest();
        String str = fileUtilsKrest.loadFileAsString(jsonFilePath);
        String str1 = "{\"request\":{\"param\":{\"entryId\":\"XXXXXX19870515XXXX\",\"dimension\":\"bgt\"}},\"head\":{\"signature\":\"....\",\"secretId\":\"....\",\"requestRefId\":\"....\"}}\n"


    }
}
