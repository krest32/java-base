package com.krest.kafka.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: krest
 * @date: 2021/6/1 08:31
 * @description:
 */
@Data
public class User implements Serializable {
    Integer id;
    String name;
}
