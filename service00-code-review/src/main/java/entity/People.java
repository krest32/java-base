package entity;

import lombok.Data;

/**
 * @author: krest
 * @date: 2021/9/5 19:20
 * @description: 作为JVM中自定义加载器的实体类，不能被删除
 */
@Data
public class People {
    private String name;
    private Integer age;

    public People() {}

    @Override
    public String toString() {
        return "I am a people, my name is " + name;
    }

}