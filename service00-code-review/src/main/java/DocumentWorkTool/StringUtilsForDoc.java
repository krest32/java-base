package DocumentWorkTool;

import java.io.FileWriter;
import java.io.IOException;

public class StringUtilsForDoc {

    /**
     * 字符串中是否包含某段字符串
     */
    public static boolean constainKey(String str, String key){
        boolean contains = str.contains(key);
        return contains;
    }

    /**
     * 将String内容输出到指定的文件当中
     */
    public static void saveAsFileWriter(String content,String FilePath) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(FilePath, true);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
