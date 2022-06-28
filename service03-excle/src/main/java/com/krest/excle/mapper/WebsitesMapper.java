package com.krest.excle.mapper;

import com.krest.excle.entity.Websites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krest
 * @since 2021-05-18
 */
public interface WebsitesMapper extends BaseMapper<Websites> {

    Websites get(String id);
}
