package com.krest.excle.service.impl;

import com.alibaba.excel.EasyExcel;
import com.krest.Service.exception.myException;
import com.krest.excle.entity.Websites;
import com.krest.excle.entity.vo.ExcelData;
import com.krest.excle.mapper.WebsitesMapper;
import com.krest.excle.service.WebsitesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.excle.utils.ExcelListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
@Service
public class WebsitesServiceImpl extends ServiceImpl<WebsitesMapper, Websites> implements WebsitesService {

    @Override
    public void importData(MultipartFile file, WebsitesService websitesService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, ExcelData.class, new ExcelListener(websitesService)).sheet().doRead();

        }catch(Exception e) {
            e.printStackTrace();
            throw new myException(20002,"添加课程分类失败");
        }
    }

    @Override
    public void batchImport(MultipartFile file) {

    }

    @Override
    public Websites get(String id) {
        Websites websites =baseMapper.get(id);
        return websites;
    }
}
