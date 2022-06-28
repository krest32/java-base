package com.krest.utils.demo;

import com.krest.utils.entity.ExcleData;
import com.krest.utils.entity.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class test2 {
    public static void main(String[] args) throws Exception {
        // 实例化一个Student对象.
        Student student = new Student(15, "HuaGe");
        System.out.println(student);

        // 将student对象写入磁盘文件(序列化)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\student.txt"));
        oos.writeObject(student);
        oos.close();

        // 从磁盘文件读取student对象(反序列化)
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\student.txt"));
        student = (Student) ois.readObject();
        System.out.println(student);

    }


}
