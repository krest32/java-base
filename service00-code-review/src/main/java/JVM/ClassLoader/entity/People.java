package JVM.ClassLoader.entity;

import lombok.Data;

/**
 * @author: krest
 * @date: 2021/9/5 19:20
 * @description:
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