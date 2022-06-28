package com.krest.utils.util;

import java.io.*;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarFileUtils {
    public void create(String jarfilename, String filename[]) {
        byte block[] = new byte[256];
        int length;
        try {

            //FileInputStream->Manifest->JarOutputStream
            //                                                                ^
            //                                                                 |
            //          FileOutputStream

            FileInputStream fis = new FileInputStream("manifest");
            Manifest man = new Manifest(fis);//创建manifest文件
            FileOutputStream fos = new FileOutputStream(jarfilename);
            JarOutputStream out = new JarOutputStream(fos, man);
            for (int i = 0; i < filename.length; i++) {
                FileInputStream in = new FileInputStream(filename[i]);
                String name = filename[i].replace(File.separatorChar, '/');

                JarEntry jarentry = new JarEntry(name);
                out.putNextEntry(jarentry);

                //FileInputStream->FileOutputStream
                while ((length = in.read(block)) > 0)
                    out.write(block, 0, length);

                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Enumeration list(String filename) {
        try (JarFile jarfile = new JarFile(filename);
        ) {
            Enumeration entrylist = jarfile.entries();
//            while(entrylist.hasMoreElements()) {
////                JarEntry jarentry = (JarEntry)entrylist.nextElement();
////                System.out.println(jarentry.getName());
////            }
            return entrylist;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void extract(String jarfilename) {
        byte block[] = new byte[256];
        int length;
        try {
            JarFile jarfile = new JarFile(jarfilename);
            Enumeration entrylist = jarfile.entries();
            while(entrylist.hasMoreElements()) {
                JarEntry jarentry = (JarEntry)entrylist.nextElement();
                InputStream in = jarfile.getInputStream(jarentry);
                String newfilename = jarentry.getName();
                FileOutputStream out = new FileOutputStream(newfilename);
                while((length = in.read(block)) > 0) {
                    out.write(block,0,length);
                }
                in.close();
                out.close();
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
