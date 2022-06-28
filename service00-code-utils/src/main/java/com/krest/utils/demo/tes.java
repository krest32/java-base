package com.krest.utils.demo;

import com.alibaba.excel.EasyExcel;
import com.krest.utils.entity.ExcleData;
import com.krest.utils.entity.ExcleData2;
import com.krest.utils.util.ExcelListener;
import com.krest.utils.util.ExcelTool;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class tes {
    @Test
    public void test(){
        String xlsxfile = "C:\\Users\\Administrator\\Desktop\\license\\Deletion.xlsx";
        EasyExcel.read(xlsxfile, ExcleData2.class, new ExcelListener()).sheet().doRead();
        System.out.println(ExcelListener.ans.size());
       ThreadLocal threadLocal = new ThreadLocal();
       threadLocal.get();
    }
}
