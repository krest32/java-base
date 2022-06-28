package com.krest.demo;

import com.krest.utils.util.FileUtilsKrest;
import com.krest.utils.util.PerCentUtil;
import com.krest.utils.util.WatchTool;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Demo3 {

    public static void main(String[] args) {
        int total = 10;
        PerCentUtil.showRatePerCent(total, 1);

    }
}
