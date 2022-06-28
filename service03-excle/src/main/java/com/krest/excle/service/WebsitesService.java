package com.krest.excle.service;

import com.krest.excle.entity.Websites;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
public interface WebsitesService extends IService<Websites> {

    void importData(MultipartFile file, WebsitesService websitesService);

    void batchImport(MultipartFile file);

    Websites get(String id);
}
