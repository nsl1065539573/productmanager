package com.example.productmanager.order.Do;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/22 4:58 PM
 **/
@Data
public class OrderDO {
  private Long id;
  private BigDecimal amount;
  private Long timeCreated;
  private Long timeUpdated;

  public OrderDO(BigDecimal amount) {
    this.amount = amount;
  }
}
