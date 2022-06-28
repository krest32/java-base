package JavaSE.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtilsKrest {
    /**
     * 测试主程序入口
     */
    public static void main(String[] args) {
    }

    /**
     * 示例一，输出指定的文件列表
     */
    public static void TestlistFiles(){
        listFiles("E:\\JavaDoc","e:\\list.txt");
    }

    /**
     * 示例二：查找指定的文件输出路径
     */
    public static void TestFind(){
        File file = new File("E:\\JavaDoc");
        String s = find(file, "centos安装标准版本.docx");
        System.out.println(s);
    }

    /**
     * 一、列出指定目录下（不包含子目录内）的所有文件名，输出到一个指定的文件当中去
     */
    public static void listFiles(String filePath , String listFilePath){
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


    /**
     * 二、递归遍历查找文件，返回路径
     */
    private static String find(File f, String name) {
        if (f.isFile()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f.getPath();
            }
        } else {
            if (!f.isDirectory()) {
                return null;
            }

            File[] fs = f.listFiles();

            for(int i = 0; i < fs.length; ++i) {
                String path = find(fs[i], name);
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

}
