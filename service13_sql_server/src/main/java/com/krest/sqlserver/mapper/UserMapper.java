package com.krest.sqlserver.mapper;

import com.krest.sqlserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: krest
 * @date: 2021/6/22 16:09
 * @description:
 */
@Mapper
public interface UserMapper {
     /**
      * 查询全部
      */
     List<User> list() ;


     /**
      * 根据id查询单条数据
      */
     User getOne(String id);


     /**
      * 更新元素
      */
     int updateUser(User user);

     /**
      * 插入元素
      */
     int insert(User user);

     /**
      * 删除元素
      */
     int delete(String id);

     /**
      * 搜索统计数量
      */
     int getCount(@Param("user") User user);


     /**
      * 无条件分页查询数据
      */
     List<User> getListPager(Map<String,Object> map);


     /**
      *  条件查询不分页
      */
     List<User> queryList(Map<String,Object> map);

}
