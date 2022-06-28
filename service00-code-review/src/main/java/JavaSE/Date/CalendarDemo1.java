package JavaSE.Date;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description：Calendar提供了世界上大多数国家使用的标准日历
 * @Author: Krest
 * @Date: 2021/9/4 22:34
 */
public class CalendarDemo1 {

    @Test
    public void test1(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        String[] item = time.split("-");
        int year  = Integer.parseInt(item[0]);
        int month = Integer.parseInt(item[1]);
        int day   = Integer.parseInt(item[2]);
        if((month - 3) <= 0){
            month = month + 12 - 3;
            year = year -1;
        }else {
            month = month - 3;
        }
        time = year + "-" + month + "-" + day;
        System.out.println(time);
    }
}
