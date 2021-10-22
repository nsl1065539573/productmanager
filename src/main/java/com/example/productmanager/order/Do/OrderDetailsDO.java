package com.example.productmanager.order.Do;

import com.example.productmanager.order.vo.OrderDetailsVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/22 4:47 PM
 **/
@Data
public class OrderDetailsDO {
  private Long id;
  private Long orderId;
  private BigDecimal amount;
  private Long productId;
  private Integer quantity;
  private Long timeCreated;
  private Long timeUpdated;

  public static OrderDetailsDO from(OrderDetailsVO origin) {
    OrderDetailsDO res = new OrderDetailsDO();
    res.setAmount(origin.getAmount());
    res.setQuantity(origin.getQuantity());
    res.setProductId(origin.getProductId());
    return res;
  }
}
