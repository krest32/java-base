package com.krest.utils.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private int age;

    private transient String name;

    public Student() {}

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}
