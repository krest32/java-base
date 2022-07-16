package com.krest.proto.demo1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTestMain3 {

    public static void main(String[] args) throws IOException {

        String[] head = new String[2];
        head[0] = "id";
        head[1] = "source";
        String fileName = "D:\\1.csv";

        List<String[]> list = new ArrayList<>();
        String[] data1 = new String[2];


        //初始化数据
        DemoPoJo.Demo.Builder builder = DemoPoJo.Demo.newBuilder();
        data1[0] = "001";
        DemoPoJo.Demo demo1 = builder.setCode("001")
                .setAddress("haha")
                .addPhone("123456")
                .addPhone("1234567")
                .putEmail("163_email", "2412341234@163.com")
                .putEmail("164_email", "2412341234@163.com")
                .build();

        data1[1] = JsonFormat.printer().print(demo1);

        list.add(data1);
        String[] data2 = new String[2];
        data2[0] = "002";
        DemoPoJo.Demo demo2 = builder.setCode("002")
                .setAddress("haha")
                .addPhone("654321")
                .addPhone("654321")
                .putEmail("163_email", "2412341234@163.com")
                .putEmail("164_email", "2412341234@163.com")
                .build();
        data2[1] = JsonFormat.printer().print(demo2);
        list.add(data2);


        CSVTool.write(fileName, head, list, true);
        String joson = CSVTool.getRowColumnText(true, fileName, 2, 2);
        DemoPoJo.Demo.Builder test = DemoPoJo.Demo.newBuilder();
        JsonFormat.parser().merge(joson, test);
        System.out.println(test);

    }

}
