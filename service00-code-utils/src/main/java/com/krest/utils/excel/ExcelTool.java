package com.krest.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.krest.utils.entity.ExcleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelTool extends AnalysisEventListener<ExcleData> {

    public static List<ExcleData> ans = new ArrayList<>();
    /**
     * 一行一行读取excel内容
     */
    @Override
    public  void invoke(ExcleData data, AnalysisContext analysisContext) {
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
}
