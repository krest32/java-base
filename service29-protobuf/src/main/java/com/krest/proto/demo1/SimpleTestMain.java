package com.krest.proto.demo1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.*;
import java.util.Arrays;

public class SimpleTestMain {

    public static void main(String[] args) throws IOException {

        //初始化数据
        DemoPoJo.Demo.Builder demo = DemoPoJo.Demo.newBuilder();
        demo
                .setCode("001")
                .setAddress("haha")
                .addPhone("123456")
                .addPhone("1234567")
                .putEmail("163_email", "2412341234@163.com")
                .putEmail("164_email", "2412341234@163.com")
                .build();


        System.out.println(demo.getId());
        // 创建可选选对象
        DemoPoJo.Mymessage.Builder worker = DemoPoJo.Mymessage.newBuilder().setDataType(DemoPoJo.Mymessage.DataType.WorkerType).setWorker(
                DemoPoJo.Worker.newBuilder().setAge(14).setName("haha")
        );

        System.out.println(JsonFormat.printer().print(worker));

        //序列化
        DemoPoJo.Demo build = demo.build();
        //转换成字节数组
        byte[] s = build.toByteArray();

        bufferedOutputStreamMethod("D:\\2.txt", s, true);
        System.out.println("protobuf数据bytes[]:" + Arrays.toString(s));
        System.out.println("protobuf序列化大小: " + s.length);


        DemoPoJo.Demo demo1 = null;
        String jsonObject = null;
        try {
            // 反序列化
            demo1 = DemoPoJo.Demo.parseFrom(s);
            //转 json
            jsonObject = JsonFormat.printer().print(demo1);
            System.out.println(jsonObject);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        bufferedWriterMethod("D:\\1.txt", jsonObject, true);
        System.out.println("Json格式化结果:\n" + jsonObject);
        System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);
    }


    public static void bufferedOutputStreamMethod(String filepath, byte[] content, boolean append) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(filepath, append))) {
            bufferedOutputStream.write(content);
        }
    }

    public static void bufferedWriterMethod(String filepath, String content, boolean append) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, append))) {
            bufferedWriter.write(content);
        }
    }
}
