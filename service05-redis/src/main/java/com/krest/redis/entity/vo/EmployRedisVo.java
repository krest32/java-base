package com.krest.redis.entity.vo;

import com.krest.redis.entity.EmployeeTbl;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/18 15:34
 * @description:
 */
@Data
@NoArgsConstructor
public class EmployRedisVo {

    List<EmployeeTbl> employeeTblList;
    Integer singin;

    public Integer getSingin() {
        int num=0;
        if(employeeTblList.size()>0){
            for (EmployeeTbl employeeTbl : employeeTblList) {
                num+=employeeTbl.getSingin();
            }
        }
        return num;
    }
}
