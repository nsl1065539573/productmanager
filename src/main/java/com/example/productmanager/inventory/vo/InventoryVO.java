package com.example.productmanager.inventory.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/13 10:33 AM
 **/
@Data
public class InventoryVO {
  private String productName; // 进货的商品名
  private Long quantity; // 进货的数量
  private BigDecimal amount; // 该类商品总价
}
