package com.krest.demo.service;

import com.krest.demo.entity.Employee;
import com.krest.demo.mapper.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     *
     * @return String
     */
    @Override
    public String create() {
        //第一种方式，直接继承xxxRepository接口
        Employee employee = Employee.builder().id("1").name("张三").password("123").build();
        employeeRepository.save(employee);
        log.info("第一种方式新增成功，employee：" + employee);

        //第二种方式，直接使用xxxTemplate
        //注意：id不能重复。MongoWriteException: E11000 duplicate key error collection: mongo.employee index: _id_ dup key: { _id: "3" }
        Employee employee2 = Employee.builder().id(UUID.randomUUID().toString()).name("李四").password("123").build();
        mongoTemplate.insert(employee2);
        log.info("第二种方式新增成功，employee：" + employee2);

        log.info("【员工接口】新增成功");
        return "新增成功";
    }

    /**
     * 更新
     *
     * @return String
     */
    @Override
    public String update() {
        //第一种方式，直接继承xxxRepository接口
        Employee employee = Employee.builder().id("1").name("张更新").password("666").build();
        employeeRepository.save(employee);

        //第二种方式，直接使用xxxTemplate
        Query query = Query.query(Criteria.where("id").is("2").and("name").is("王小二"));
        Update update = Update.update("name", "王更新");
        mongoTemplate.updateFirst(query, update, Employee.class);

        log.info("【员工接口】更新成功");
        return "更新成功";
    }

    /**
     * 删除
     *
     * @return String
     */
    @Override
    public String delete() {
        //第一种方式，直接继承xxxRepository接口
        employeeRepository.deleteById("1");

        //第二种方式，直接使用xxxTemplate
        Query query = Query.query(Criteria.where("id").is("2"));
        mongoTemplate.remove(query, Employee.class);

        log.info("【员工接口】删除成功");
        return "删除成功";
    }

    /**
     * 查询
     *
     * @return String
     */
    @Override
    public List<Employee> select() {
        //第一种方式，直接继承xxxRepository接口
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("第一种方式，employeeList：" + employeeList);

        //第二种方式，直接使用xxxTemplate
        List<Employee> employeeLists = this.mongoTemplate.findAll(Employee.class);
        System.out.println("第二种方式，employeeList：" + employeeLists);

        log.info("【员工接口】查询成功");
        return employeeLists;
    }
}
