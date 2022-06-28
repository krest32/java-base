package com.krest.product.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class OrderPaidEvent  implements Serializable {

    private String orderId;
    private Integer paidMoney;
}
