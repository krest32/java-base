package com.krest.Gateway.entity;

import lombok.*;

/**
 * 描述:
 *
 * @Auther: lzx
 * @Date: 2019/7/9 13:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnData {

    private int code;

    private String mass;

    private String data;

}
