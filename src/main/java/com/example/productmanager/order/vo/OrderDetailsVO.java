package com.example.productmanager.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/22 3:45 PM
 **/
@Data
public class OrderDetailsVO {
  private Long productId; // 商品id
  private BigDecimal amount; // 商品单价
  private Integer quantity; // 商品卖出的数量
}
