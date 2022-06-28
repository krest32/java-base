package com.krest.sqlserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: krest
 * @date: 2021/6/22 16:08
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinterUser implements Serializable {
    private static final long serialVersionUID = 1L;
    String id;
    String name;
    String gender;
    Integer age;
}
