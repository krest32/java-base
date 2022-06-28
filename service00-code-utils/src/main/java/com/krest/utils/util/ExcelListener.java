package com.krest.utils.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.krest.utils.entity.ExcleData;
import com.krest.utils.entity.ExcleData2;

import java.io.*;
import java.util.*;

/**
 * @author: krest
 * @date: 2021/5/18 01:03
 * @description: Excle 导入工具类
 */
public class ExcelListener extends AnalysisEventListener<ExcleData2> {
    /**
     * 一行一行读取excel内容
     */
    public static List<ExcleData2> ans = new ArrayList<>();

    @Override
    public void invoke(ExcleData2 data, AnalysisContext analysisContext) {
        System.out.println("****" + data);
        ans.add(data);
    }

    /**
     * 读取表头内容
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }

    /**
     * 读取完成之后
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public static void writeExcel(String fileName, List<ExcleData2> list){
        EasyExcel.write(fileName, ExcleData2.class).sheet("模板").doWrite(list);
    }

}
