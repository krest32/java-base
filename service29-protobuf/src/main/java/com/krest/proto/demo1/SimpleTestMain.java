package com.krest.proto.demo1;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.util.Arrays;

public class SimpleTestMain {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        //初始化数据
        DemoPoJo.Demo.Builder demo = DemoPoJo.Demo.newBuilder();
        demo.setId(1)
                .setCode("001")
                .setName("张三")
                .setAddress("haha")
                .addPhone("123456")
                .addPhone("1234567")
                .putEmail("163_email","2412341234@163.com")
                .putEmail("164_email","2412341234@163.com")
                .build();

        // 创建可选选对象
        DemoPoJo.Mymessage.Builder worker = DemoPoJo.Mymessage.newBuilder().setDataType(DemoPoJo.Mymessage.DataType.WorkerType).setWorker(
                DemoPoJo.Worker.newBuilder().setAge(14).setName("haha")
        );

        System.out.println(JsonFormat.printer().print(worker));


        //序列化
        DemoPoJo.Demo build = demo.build();
        //转换成字节数组
        byte[] s = build.toByteArray();
        System.out.println("protobuf数据bytes[]:" + Arrays.toString(s));
        System.out.println("protobuf序列化大小: " + s.length);


        DemoPoJo.Demo demo1 = null;
        String jsonObject = null;
        try {
            //反序列化
            demo1 = DemoPoJo.Demo.parseFrom(s);
            //转 json
            jsonObject = JsonFormat.printer().print(demo1);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.println("Json格式化结果:\n" + jsonObject);
        System.out.println("Json格式化数据大小: " + jsonObject.getBytes().length);
    }
}
