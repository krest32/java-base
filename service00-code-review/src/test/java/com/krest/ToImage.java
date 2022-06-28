package com.krest;

import gui.ava.html.image.generator.HtmlImageGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;


public class ToImage {
    public static void main(String[] args) {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        String htmlstr = "<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"> <tbody><tr><td>&nbsp;</td></tr> <tr><td>&nbsp;</td></tr> <tr><td>&nbsp;</td></tr> <tr><td>&nbsp;</td></tr> <tr> <td><div align=\"center\"><strong>提示信息</strong></div></td> </tr> </tbody></table> <table width=\"40%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bordercolor=\"#A6C5E7\"> <tbody><tr><td bordercolor=\"#FFFFFF\" bgcolor=\"#FFFFFF\"> <table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"scrolldiv1\"><tbody><tr><td></td></tr><tr> <td><div align=\"center\"><p class=\"style1\"><strong>&nbsp;&nbsp;&nbsp;<font color=\"#FF0000\">本地调取</font>中征码与企业名称不匹配！</strong></p > </div></td> </tr></tbody></table><table width=\"60%\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#A6C5E7\"><tbody><tr><td class=\"tdStyle1 style1\" width=\"40%\"><b>录入企业名称</b></td><td id=\"EnterpriseName\" class=\"tdStyle1 style1\"><b>上海华钇铄普精密机械制造有限公司</b></td> </tr> <tr><td class=\"tdStyle1 style1\"><b>中征码对应的企业名称</b></td><td id=\"PBOCenterpriseName\" class=\"tdStyle1 style1\"><b>长沙晴雨雷机电贸易有限公司</b></td></tr> </tbody></table> &nbsp;<center> <input onclick=\"javascript:window.close();return false;\" class=\"input-button\" style=\"FONT-SIZE: 12px; FONT-VARIANT: normal; WIDTH: 45px; FONT-WEIGHT: normal; FONT-STYLE: normal; LINE-HEIGHT: normal\" type=\"button\" value=\"关 闭\" /></center> </td></tr></tbody></table>";
        try {
            imageGenerator.loadHtml(htmlstr); // 加载html源码内容
            imageGenerator.getBufferedImage(); // 生成图片字符流
            imageGenerator.saveAsImage("D:\\10001.png"); // 保存到本地
            System.out.println("----end----");
            // imageGenerator.saveAsHtmlWithMap("/home/demo1/dev/hello-world.html",
            // "/home/demo1/dev/hello-world.png");  //把图片转成网页，只是简单的img 引用
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
