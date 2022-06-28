package com.krest.mybatis.mapper;

import com.krest.mybatis.entity.Apps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: krest
 * @date: 2021/5/18 21:06
 * @description:
 */

@Resource
public interface AppsMapper {

    /**
     * 根据id查询单条数据
     */
    Apps getOne(String id);

    /**
     * 查询所有数据
     */
    List<Apps> getAll();

    /**
     * 根据id删除指定的元素
     */
    int deleteApp(String id);

    /**
     * 更新元素
     */
    int updateApps(Apps apps);

    /**
     *  根据条件统计数量
     */
    int getCount(@Param("app") Apps app);


    /**
     * 无条件分页查询数据
     */
    List<Apps> getListPager(Map<String,Object> map);

    /**
     *  条件查询不分页
     */
    List<Apps> queryList(Map<String,Object> map);

    /**
     *  根据输入的条件进行查询所有数据
     */
    List<Apps> selectBySql(@Param("sql") String sql);

}
