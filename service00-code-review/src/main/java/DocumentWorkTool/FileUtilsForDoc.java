package DocumentWorkTool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtilsForDoc {

    /**
     * 示例一，输出指定的文件列表
     */
    public static void TestlistFiles(){
        listFiles("E:\\JavaDoc","e:\\list.txt");
    }

    /**
     * 一、列出制定目录下（不包含子目录内）的所有文件名，输出到一个指定的文件当中去
     */
    public static void listFiles(String filePath,String listFilePath){
        File file = new File (filePath);
        File[] files = file.listFiles();
        try{
            File file2 = new File (listFilePath);
            FileWriter fw = null;
            try {
                fw = new FileWriter(file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter pw = new PrintWriter(fw,true);
            for (int i=0;i<files.length;i++){
                pw.println(files[i].getName());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
