package com.krest.xml.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author: krest
 * @date: 2021/6/22 11:04
 * @description:
 */
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Object data;
}
