package com.krest.toImage.utils;

import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ToImageUtil {

    public static void  toImage(String HtmlTemplateStr, String path){
        HtmlParser htmlParser = new HtmlParserImpl();
        htmlParser.loadHtml(HtmlTemplateStr);
        // html代码
        ImageRenderer imageRenderer = new ImageRendererSubImpl(htmlParser);
        imageRenderer.saveImage(path);

    }


    public static void showImageUrl() {
    }

    //
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
