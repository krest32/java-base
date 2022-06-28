package JavaSE.IO.entity;

/**
 * @Description：
 * @Author: Krest
 * @Date: 2021/9/4 22:03
 */
public class Member {

    private String name;
    private int age;
    public Member() {
    }
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
