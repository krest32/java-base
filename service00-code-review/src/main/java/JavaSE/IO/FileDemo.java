package JavaSE.IO;

import java.io.File;

/**
 *递归读取文件件的文件名称
 */
public class FileDemo {

    public static void list(String path){
        if(path == null){
            return;
        }
        File[] files = new File(path).listFiles();
        if(files == null){
            return;
        }
        for (File file: files){
            if (file.isFile()){
                System.out.println(file.getName());
            }else if(file.isDirectory()){
                System.out.println("Directory:" + file.getName());
                list(file.getPath());
            }else{
                System.out.println("error");
            }
        }
    }

    public static void main(String[] args) {
        FileDemo.list("D:\\");
    }
}
