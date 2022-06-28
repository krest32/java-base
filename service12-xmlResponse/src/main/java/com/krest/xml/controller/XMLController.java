package com.krest.xml.controller;

import com.krest.Service.Response.R;
import com.krest.xml.entity.Site;
import com.krest.xml.entity.Student;
import com.krest.xml.service.XMLService;
import com.krest.xml.utils.XMLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author: krest
 * @date: 2021/6/22 11:00
 * @description: XMl请求响应测试
 */
@Api(tags = "XMl请求响应测试")
@RequestMapping("/app/xml")
@RestController
public class XMLController {

    @Autowired
    private XMLService xmlService;
    /**
     * 将对象转化为字符串
     */
    @ApiOperation("将对象转化为xml字符串")
    @PostMapping("xmlResponse")
    public R xmlResponse(@RequestBody Student student) throws Exception {
        String s = XMLUtil.convertToXml(student);
        System.out.println(s);
        return R.ok().data("xmlString", s);
    }

    @ApiOperation("Denny:解析XML字符串")
    @PostMapping("analysisXML")
    public R analysisXML(@RequestParam String res) throws TransformerException {
        return xmlService.analysisXML(res);
    }


}
