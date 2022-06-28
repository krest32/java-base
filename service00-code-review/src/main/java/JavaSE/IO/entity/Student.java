package JavaSE.IO.entity;

import java.io.Serializable;

/**
 * @Description：
 * @Author: Krest
 * @Date: 2021/9/5 0:10
 */
public class Student implements Serializable {
    private String name;
    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}
