package com.krest.proto.demo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCsv {
    public static void main(String[] args) throws IOException {
        String[] head = new String[2];
        head[0] = "id";
        head[1] = "content";
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            String[] temp = new String[2];
            temp[0] = String.valueOf(i);
            temp[1] = "fsadddddddddddddddddddddddddddddddddddddddddddddddddfawefwefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";
            list.add(temp);
        }

        Long start = System.currentTimeMillis();
        CSVTool.write("D:\\1.csv", head, list, true);
        Long end = System.currentTimeMillis();
        System.out.println((end - start) * 0.001);
    }
}
