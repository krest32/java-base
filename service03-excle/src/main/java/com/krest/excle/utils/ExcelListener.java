package com.krest.excle.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.krest.Service.exception.myException;
import com.krest.excle.entity.Websites;
import com.krest.excle.entity.vo.ExcelData;
import com.krest.excle.service.WebsitesService;

import java.util.Map;

/**
 * @author: krest
 * @date: 2021/5/18 01:03
 * @description: Excle 导入工具类
 */
public class ExcelListener extends AnalysisEventListener<ExcelData> {


    public WebsitesService websitesService;

    public ExcelListener() {}

    /**
     * 创建有参数构造，传递 websitesService ，用于操作数据库
     */
    public ExcelListener(WebsitesService websitesService) {
        this.websitesService = websitesService;
    }

    /**
     * 读取excle的表头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }


    /**
     * 一行一行的读取数据
     * @param data
     * @param context
     */
    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        if(data == null){
            throw new myException(20001,"添加失败");
        }
        System.out.println(data);
        // 想数据库中添加数据
        Websites websites = new Websites();
        websites.setName(data.getName());
        websites.setUrl(data.getUrl());
        websites.setAlexa(data.getAlexa());
        websites.setCountry(data.getCountry());
        websitesService.save(websites);
    }

    /**
     * excle读取完之后进行执行
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
