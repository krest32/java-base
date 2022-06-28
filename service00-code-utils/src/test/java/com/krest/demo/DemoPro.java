package com.krest.demo;

import com.krest.utils.entity.Student;
import com.krest.utils.util.FileUtilsKrest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class DemoPro {

    public static void main(String[] args) {
        String file = "D:\\BlazeIntegrationFiles\\{date}\\AI\\{appId}_{callType}_{callRound}_AIScoring_Input.txt";
        String date = "2020-11-20";
        String appId = "1234567892";
        String callType = "call1";
        String callRound = "1";

        file = file.replace("{date}", date)
                .replace("{appId}", appId)
                .replace("{callType}", callType)
                .replace("{callRound}", callRound);

        FileUtilsKrest.saveStringToFile(
                "aaa", file
        );
    }


    private static void test() {
        String folder = "D:\\test\\";
        FileUtilsKrest fileTool = new FileUtilsKrest();
        int times = 10;
        List<String> list = new ArrayList<>();
        list.add("aaa");
        for (int i = 0; i < 100_000; i++)
            list.add(UUID.randomUUID().toString());

        creatFile(folder, fileTool, times);
        System.out.println("创建文件完成:" + fileTool.getAllFile(folder).size());
        delFile(fileTool, folder, list);
        System.out.println("完成删除任务");
    }

    private static void creatFile(String folder, FileUtilsKrest fileTool, int times) {
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < times; j++) {
                String file = folder + i + "\\" + j + "\\" + "aaa" + UUID.randomUUID() + ".txt";
                fileTool.writeStr(file, "aaa", false);
            }
        }
    }

    private static void delFile(FileUtilsKrest fileTool, String folder, List<String> list) {
        long start = System.currentTimeMillis();
        System.out.println("开始删除任务:" + fileTool.delFileWithKeyWords(folder, list, true, false));
        long end = System.currentTimeMillis();
        System.out.println((end - start) * 0.001);
    }
}
