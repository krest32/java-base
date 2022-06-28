package com.krest.demo.mapper;

import com.krest.demo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String>  {
}
