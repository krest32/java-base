package com.krest.excle.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.krest.excle.entity.vo.OutputExcleData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/18 01:32
 * @description: Excle导出工具类
 */
public class OutputExcelUtil {
    /**
     * 导出excel工具类
     * ComparisonModel 实体类
     * @param response
     * @param list
     * @throws
     */
    public static  void  writeExcel(HttpServletResponse response, List<OutputExcleData> list) throws IOException {
        ExcelWriter writer = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet = EasyExcel.writerSheet(0,"sheet").head(OutputExcleData.class).build();
        writer.write(list,sheet);
        writer.finish();
    }
}
