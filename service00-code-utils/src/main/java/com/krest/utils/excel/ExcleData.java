package com.krest.utils.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

@Data
public class ExcleData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "文档名称", index = 0)
    String title;
    @ExcelProperty(value = "关键字组合", index = 1)
    String keyWords;
    @ExcelProperty(value = "文档链接", index = 2)
    String link;
    @ExcelProperty(value = "知识内容赋能专区", index = 3)
    String classify;

}
