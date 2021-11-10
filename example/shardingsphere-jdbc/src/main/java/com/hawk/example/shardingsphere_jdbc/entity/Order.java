package com.hawk.example.shardingsphere_jdbc.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hawk
 * @package com.hawk.example.shardingsphere_jdbc.entity
 * @desc
 * @date 2021/11/10
 */
@Data
@Builder
public class Order {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer state;
}
