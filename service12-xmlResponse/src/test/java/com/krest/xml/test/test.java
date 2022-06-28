package com.krest.xml.test;

import com.krest.xml.entity.Student;
import com.krest.xml.utils.javaToxml;
import org.junit.Test;

/**
 * @author: krest
 * @date: 2021/6/22 11:10
 * @description:
 */
public class test {

    @Test
    public void test() throws Exception {
        Student stu = new Student("001","Tom",22,"hahaha");
        javaToxml.javaToxml(stu);

    }
}
