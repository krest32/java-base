package com.krest.utils.demo;

import com.alibaba.excel.EasyExcel;
import com.krest.utils.entity.ExcleData;
import com.krest.utils.entity.ExcleData2;
import com.krest.utils.util.CSVTool;
import com.krest.utils.util.ExcelListener;

import java.io.IOException;
import java.util.*;


public class test {
    public static void main(String[] args) throws IOException {
        Stack stack = new Stack();
        //实现excel读操作
        String filename = "C:\\Users\\Administrator\\Desktop\\vs work\\未命名表格.xlsx";
        EasyExcel.read(filename, ExcleData.class, new ExcelListener()).sheet().doRead();
        System.out.println(ExcelListener.ans.size());

        // 设置表头
        String[] head = {"name", "stuff", "link", "difficulty", "tags", "methods", "tools"};
        List<String[]> ans = new ArrayList<>();
//        for (int i = 0; i < ExcelListener.ans.size(); i++) {
//            ExcleData2 data = ExcelListener.ans.get(i);
//            ans.add(new String[]{data.getTitle(), data.getKeyWords(), data.getLink(), null, null, null, data.getClassify()});
//        }
//        String csvFile = "C:\\Users\\Administrator\\Desktop\\vs work\\cook-vedio\\src\\data\\recipe.csv";
//        CSVTool.write(csvFile, head, ans);
    }



}
