package com.krest.toImage.controller;

import com.krest.toImage.utils.ToImageUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;

@RestController
@RequestMapping("/toImageService")
public class ToImageController {
    @PostMapping("htmlStrToImageFile")
    public void toImage(@RequestParam String htmlStr,
                          @RequestParam String path){
       ToImageUtil.toImage(htmlStr,path);
    }

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }


    /**
     * 将本地图片，通过网址url的形式显示给用户
     * @param fileName  图片名称
     * @param response  http显示内容
     */
    @GetMapping("showImageUrl/{fileName}")
    public void showImageUrl(@PathVariable String fileName, HttpServletResponse response){
        try {
            File imagefile= new File("d:\\"+fileName);
            InputStream inputStream = new FileInputStream(imagefile);
            byte data[] = ToImageUtil.readInputStream(inputStream);
            //设置返回的文件类型
            response.setContentType("image/jpg");
            OutputStream os = response.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
