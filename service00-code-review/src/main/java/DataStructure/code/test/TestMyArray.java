package DataStructure.code.test;

import DataStructure.code.liner.MyArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author: krest
 * @date: 2021/1/3 10:23
 * @description: Test Program
 */
public class TestMyArray {
    public static void main(String[] args) {
        new ArrayList<>();
        System.out.println();
        List myList = new MyArrayList(3);
        myList.add(123);
        myList.add(234);
        myList.add(235);
        myList.add(435);
        myList.add(123);
        myList.add(234);
        myList.add(19,88888);
        myList.remove(19);
        System.out.println(myList.size());
        System.out.println(myList.isEmpty());
        System.out.println(myList);
        System.out.println(myList.get(19));

        TreeSet<Object> objects = new TreeSet<>();
        objects.add(123);


    }
}
