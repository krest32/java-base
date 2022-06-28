package zk.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: krest
 * @date: 2021/5/31 18:39
 * @description:
 */
@Data
public class User implements Serializable {
    Integer age;
    String name;
    String id;
}
