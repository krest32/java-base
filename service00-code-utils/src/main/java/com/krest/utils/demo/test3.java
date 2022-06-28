package com.krest.utils.demo;


import com.krest.utils.entity.ExcleData2;
import com.krest.utils.util.CSVTool;
import com.krest.utils.util.ExcelListener;

import java.util.List;

public class test3 {
    public static void main(String[] args) throws Exception {
        String csvFile = "C:\\Users\\Administrator\\Desktop\\license\\LICENSE_THIRD_PARTY.csv";
        String xlsxFile = "C:\\Users\\Administrator\\Desktop\\license\\LICENSE_THIRD_PARTY.xlsx";
        List<ExcleData2> ans = CSVTool.test(csvFile);
        ExcelListener.writeExcel(xlsxFile,ans);
//        File ansfile = new File(xlsxFile);
//        List<String> text = CSVTool.getText(csvFile);
//        List<ExcleData2> ans = new ArrayList<>();
//        for (String str : text) {
//            String[] split = str.split(",");
//            ExcleData2 data = new ExcleData2();
//            data.setLicense(split[0].trim());
//            data.setName(split[1].trim());
//            data.setArtifact(split[2].trim());
//            data.setURL(split[3].trim());
//            System.out.println(data.toString());
//            ans.add(data);
//        }
//
//        ExcelListener.writeExcel(xlsxFile,ans);

    }
}
