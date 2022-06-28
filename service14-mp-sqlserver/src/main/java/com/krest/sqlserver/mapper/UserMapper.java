package com.krest.sqlserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.krest.sqlserver.entity.WinterUser;

/**
 * @author: krest
 * @date: 2021/6/22 16:09
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<WinterUser> {


}
