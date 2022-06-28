package com.krest.mybatis.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: krest
 * @date: 2021/5/18 21:06
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apps implements Serializable {

    private String id;

    private String appName;

    private String url;

    private String country;


}
