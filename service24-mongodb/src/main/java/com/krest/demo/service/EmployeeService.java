package com.krest.demo.service;

import com.krest.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * 新增
     *
     * @return String
     */
    String create();

    /**
     * 更新
     *
     * @return String
     */
    String update();

    /**
     * 删除
     *
     * @return String
     */
    String delete();

    /**
     * 查询
     *
     * @return String
     */
    List<Employee> select();
}
