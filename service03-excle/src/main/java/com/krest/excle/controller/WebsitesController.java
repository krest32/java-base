package com.krest.excle.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.krest.Service.Response.R;
import com.krest.excle.entity.Websites;
import com.krest.excle.entity.vo.OutputExcleData;
import com.krest.excle.service.WebsitesService;
import com.krest.excle.utils.OutputExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/excle/websites")
public class WebsitesController {

    @Autowired
    private WebsitesService websitesService;


    @GetMapping("hello")
    public String hello(){
        return "你好";
    }

    @GetMapping("GetAllInfo")
    public R GetAllInfo(){
        List<Websites> list = websitesService.list(null);
        return R.ok().data("list",list);
    }

    @RequestMapping(value = "getOne",method = RequestMethod.GET)
    public R getOne(@RequestParam(name = "id") String id){
        Websites websites = websitesService.get(id);
        return R.ok().data("data",websites);
    }

    /**
     *  批量导出
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addWebsite")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        websitesService.importData(file,websitesService);
        //判断返回集合是否为空
        return R.ok();
    }

    /**
     * 导出
     * list数据是随便写的用来测试的，自己可以通过任何方法来获取。
     * ComparisonModel  实体类
     */
    @GetMapping("/writeExcel") //使用post方式会出错，请使用get方式请求。
    public void writeExcel(HttpServletResponse response) throws Exception {
        List<OutputExcleData> list = new ArrayList();
        QueryWrapper<Websites> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("alexa");
        List<Websites> websitesList = websitesService.list(queryWrapper);
        for (Websites websites : websitesList) {
            OutputExcleData  com = new OutputExcleData ();
            com.setTitle(websites.getName());
            com.setUrl(websites.getUrl());
            com.setAlexa(websites.getAlexa());
            com.setCountry(websites.getCountry());
            list.add(com);
        }
        //不需要改动，response返回excle文件
        response.setContentType("application/vnd.ms-excel;chartset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+"data.xlsx");
        //调用工具类
        OutputExcelUtil.writeExcel(response,list);
    }

}

