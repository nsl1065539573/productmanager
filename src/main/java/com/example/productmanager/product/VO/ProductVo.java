package com.example.productmanager.product.VO;

import com.example.productmanager.product.DO.ProductDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/8 5:12 PM
 **/
@Data
public class ProductVo {
  private Long id;
  private String name;
  private BigDecimal price;
  private Long timeCreated;
  private Long timeUpdated;

  public static ProductVo from(ProductDO origin) {
    ProductVo result = new ProductVo();
    result.id = origin.getId();
    result.name = origin.getName();
    result.price = origin.getPrice();
    result.timeCreated = origin.getTimeCreated();
    result.timeUpdated = origin.getTimeUpdated();
    return result;
  }
}
