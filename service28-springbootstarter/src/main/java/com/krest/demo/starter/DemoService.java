package com.krest.demo.starter;

public class DemoService {
    private String name;
    private String address;

    public DemoService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String sayHello(){
        return "你好！我的名字叫 " + name + "，我来自 " + address;
    }
}
