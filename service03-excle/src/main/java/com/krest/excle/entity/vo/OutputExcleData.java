package com.krest.excle.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: krest
 * @date: 2021/5/18 01:31
 * @description: 数据到处时，表头定义内容
 */
@Data
public class OutputExcleData {
    @ExcelIgnore
    private Integer comparisonId; //ID不需要导出，所以隐藏

    @ExcelProperty("名称")
    private String title;

    @ExcelProperty("地址")
    private String url;

    @ExcelProperty("点击率")
    private String alexa;

    @ExcelProperty("国家")
    private String country;

    //@ExcelProperty("创建日期")
    //@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    //private Date createDate;

}
