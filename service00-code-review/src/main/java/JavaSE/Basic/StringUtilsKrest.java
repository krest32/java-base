package JavaSE.Basic;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * version 20210716
 */
public class StringUtilsKrest {
    
    /**
     * 字符串中是否包含某段字符串
     */
    public static boolean isConstainSubString(String str, String key){
        boolean contains = str.contains(key);
        return contains;
    }

    /**
     * 将字符串存储为文件，
     * 三个参数：要保存的字符串、文件保存目录、是否在原文件上进行追加
     */
    public static void saveStrAsFileWriter(String content,String FilePath,boolean isAppend) throws IOException {
        if (content.equals("")) {
            throw new IllegalArgumentException("Operate File's Name Argument JavaSe.Exception.");
        } else {
            File operFile = new File(FilePath);
            if (operFile.isDirectory()) {
                throw new IOException("Operate File is folder.");
            } else {
                File checkFolder = new File(operFile.getParent());
                if (!checkFolder.exists() || checkFolder.isFile()) {
                    checkFolder.mkdirs();
                }
                operFile.deleteOnExit();
                OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(operFile), "UTF-8");
                os.write(content);
                os.flush();
                os.close();
            }
        }
    }

    /**
     * 转化字符串为数字，小数使用BigDecimal，整数使用Integer和 Long类型
     * System.out.println(strToDecimalPlaces("-123.336",2)); 输出为-123.34
     */
    public static Object strToNumber(String str) throws Exception {
        // 不限制小数的长度
        String rex = "^[-\\+]?([0-9]+\\.?)?[0-9]+$";
        try{
            if(str!=null && str.matches(rex) && str.length()< 10 ){
                if(str.startsWith("00")){
                    return str;
                }
                BigDecimal bigDecimalRes = new BigDecimal(str);
                if(str.matches("^[-\\+]?[1-9]\\d*$") && bigDecimalRes instanceof BigDecimal && !str.contains(".")){
                    Long longRres =Long.parseLong(str);
                    if(str.matches("^[-\\+]?[1-9]\\d*$") && !str.contains(".") && longRres <= Integer.MAX_VALUE && longRres >= Integer.MIN_VALUE){
                        Integer intRes = Integer.parseInt(str);
                        return intRes;
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
    public static Object strToDecimalPlaces(String str,Integer placeNumber ){
        String rex = "^[-\\+]?([0-9]+\\.?)?[0-9]+$";
        try{
            if(str.matches(rex)){
                String fromat = new String("0.");
                for(int i=0; i<placeNumber; i++){
                    fromat = fromat+"0";
                }
                BigDecimal res = new BigDecimal(str);
                DecimalFormat decimalFormat = new DecimalFormat(fromat);
                String format = decimalFormat.format(res);
                return format;
            }
        }catch (Exception e){
            return str;
        }
        return str;
    }

}
