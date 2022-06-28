package com.krest.utils.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * version 20210716
 */
public class StringUtilsKrest {

    static String rex = "^[-\\+]?([0-9]+\\.?)?[0-9]+$";

    private StringUtilsKrest(){}
    
    /**
     * 字符串中是否包含某段字符串
     */
    public static boolean isContainSubString(String str, String key){
        return str.contains(key);
    }

    /**
     * 转化字符串为数字，小数使用BigDecimal，整数使用Integer和 Long类型，根据类型转化
     * System.out.println(strToDecimalPlaces("-123.336",2)); 输出为-123.34
     */
    public static Object strToNumber(String str) {
        if (str.equals("")){
            return null;
        }
        // 不限制小数的长度
        try{
            if(str.matches(rex) && str.length()< 10 ){
                if(str.startsWith("00")){
                    return str;
                }
                BigDecimal bigDecimalRes = new BigDecimal(str);
                if(str.matches("^[-\\+]?[1-9]\\d*$") && bigDecimalRes instanceof BigDecimal && !str.contains(".")){
                    Long longRres =Long.parseLong(str);
                    if(str.matches("^[-\\+]?[1-9]\\d*$") && !str.contains(".") && longRres <= Integer.MAX_VALUE && longRres >= Integer.MIN_VALUE){
                        return  Integer.parseInt(str);
                    }else{
                        return longRres;
                    }
                }else{
                    return bigDecimalRes;
                }
            }
        }catch (Exception e){
            return str;
        }
        return str;
    }

    /**
     * 转换Str小数为固定位数小数,数字会进行四舍五入处理
     */
    public static BigDecimal strToDecimalPlaces(String str,Integer placeNumber ){
        try{
            if(str.matches(rex)){
                StringBuilder fromat =new StringBuilder("0.");
                for(int i=0; i<placeNumber; i++){
                    fromat.append("0");
                }
                BigDecimal res = new BigDecimal(str);
                DecimalFormat decimalFormat = new DecimalFormat(fromat.toString());
                String format = decimalFormat.format(res);
                return new BigDecimal(format);
            }
        }catch (Exception e){
            return BigDecimal.valueOf(0.00);
        }
        return BigDecimal.valueOf(0.00);
    }
    /**
     * 转化字符串为Integer,字符串长度不能超过 10
     */
    public static Integer strToInteger(String str) {
        if (str == null || str.equals("")){
            return 0;
        }
        try{
            if(str.matches(rex) && str.length()<10){
                if(str.startsWith("00")){
                    return null;
                }
                BigDecimal bigDecimalRes = strToDecimalPlaces(str,0);
                return bigDecimalRes.intValue();
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }

    /**
     * 数据脱敏 desensitization
     */
    public static String dataDesensitization(String str){
        int nextInt = new Random().nextInt(str.length());
        StringBuilder res = new StringBuilder();
        for(int i=0; i<str.length();i++){
            if (i==nextInt){
                res.append("*");
            }else{
                res.append(str.charAt(i));
            }
        }
        return new String(res);
    }

    /**
     * 数据脱敏将第二字符用*代替
     */
    public static String desensitization(String str){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<str.length();i++){
            if (i==1){
                res.append("*");
            }else{
                res.append(str.charAt(i));
            }
        }
        return new String(res);
    }


    /**
     *  判断一个字符串是否含有数字
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 替换字符串中所有的数字为想要的字符
     */
    public static String replaceChar(String str, Character newChar){
        Pattern p = compile("[0-9]");
        Matcher matcher = p.matcher(str);
        return matcher.replaceAll(String.valueOf(newChar));
    }

    /**
     *  判断String能否转化为数字
     */
    public static boolean isInteger(String str){
        try{
            Integer.valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 将数字转换位千进制
     * @param bigDecimal
     * @return
     */
    public static BigDecimal getFormatThousand(BigDecimal bigDecimal) {
        //转换为千元
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("1000"));
        // 保留两位小数 ss="###.##"
        String format = "###.#";
        DecimalFormat formater = new DecimalFormat(format);
        //向下取整的方式
        formater.setRoundingMode(RoundingMode.DOWN);
        //格式化完成之后得出结果
        String rs = formater.format(decimal);
        BigDecimal places = new BigDecimal(rs);

        if (bigDecimal.compareTo(new BigDecimal(100))<0 && bigDecimal.compareTo(new BigDecimal(0))>0){
            rs = formater.format(0.1);
            places = new BigDecimal(rs);
            return places;
        }
        return places;
    }

    /**
     * 计算两个数字的百分比
     */
    public static String percenTage(BigDecimal total, BigDecimal used){
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        float baifen = used.floatValue()/total.floatValue();
       return nt.format(baifen);
    }
}
