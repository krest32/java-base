package com.krest.utils.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

import java.beans.Transient;
import java.io.Serializable;

@Data
@ToString
public class ExcleData2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "FOSS Component", index = 0)
    String FOSS_Component_Name;
    @ExcelProperty(value = "Version", index = 1)
    String Version;
    @ExcelProperty(value = "FOSS_license", index = 2)
    String FOSS_license;
    @ExcelProperty(value = "CopyRight Information", index = 3)
    String CopyRight_Information;
}
