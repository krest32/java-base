package com.krest;

import java.io.File;

public class delete {
    public static void main(String[] args) {
        String files = "D:\\test\\1_*";
        File file = new File(files);
        File parentFile = file.getParentFile();
        String fileLikes = file.getName().replace("*", "");
        if (parentFile.exists() && parentFile.isDirectory()) {
            File[] list = parentFile.listFiles();
            for (File fileName : list) {
                if (fileName.getName().contains(fileLikes)){
                    System.out.println(fileName);
                }
            }
        }
    }
}
