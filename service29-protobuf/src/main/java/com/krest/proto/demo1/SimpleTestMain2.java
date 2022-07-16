package com.krest.proto.demo1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.*;
import java.util.Arrays;

public class SimpleTestMain2 {

    public static void main(String[] args) throws IOException {

        //初始化数据
        DemoPoJo.Demo.Builder builder = DemoPoJo.Demo.newBuilder();
        DemoPoJo.Demo demo1 = builder.setId(1).build();
        System.out.println(demo1.getId());

        DemoPoJo.Demo.Builder builder2 = DemoPoJo.Demo.newBuilder();
        DemoPoJo.Demo demo2 = builder2.setId(2).build();

        System.out.println(demo1.getId());
        System.out.println(demo2.getId());

        System.out.println(builder.hashCode());
        System.out.println(builder2.hashCode());
    }
}
