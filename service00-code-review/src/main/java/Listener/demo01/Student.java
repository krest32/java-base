package Listener.demo01;


import lombok.Data;

import java.util.List;

@Data
public class Student extends StudentChange {

    public static final String TEST = "test";

    private List<String> phoneList;

    private String name;

    public void setName(String name) {
        if (name.equals(this.name)) {
            System.out.println("BeanTest 的 name 属性没有变化！");
            return;
        }
        String oldName = this.name;
        this.name = name;
        firePropertyChange(TEST, oldName, name);
    }
}
