package com.krest.redis.entity.vo;

import com.krest.redis.entity.EmployeeTbl;
import lombok.Data;

import java.util.Date;

/**
 * @author: krest
 * @date: 2021/5/18 16:34
 * @description:
 */
@Data
public class Comment {
    EmployeeTbl employeeTbl;
    String comment;
    Date date;
}
