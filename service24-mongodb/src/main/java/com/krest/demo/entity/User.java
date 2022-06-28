package com.krest.demo.entity;

import lombok.Data;

@Data
public class User {
    //姓名
    private String name;
    //年龄
    private Integer age;
    //等级
    private Integer grade;
}