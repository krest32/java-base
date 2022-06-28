package com.krest.excle.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: krest
 * @date: 2021/5/18 01:00
 * @description: Excle 导入后自定义表头
 */
@Data
public class ExcelData {
    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String url;

    @ExcelProperty(index = 2)
    private String alexa;

    @ExcelProperty(index = 3)
    private String country;
}
