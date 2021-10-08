package com.example.productmanager.product.DO;

import com.example.productmanager.product.VO.ProductVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/8 4:57 PM
 **/
@Data
public class ProductDO {
  private Long id;
  private String name;
  private BigDecimal price;
  private Long timeCreated;
  private Long timeUpdated;

  public static ProductDO from(ProductVo origin) {
    ProductDO result = new ProductDO();
    result.id = origin.getId();
    result.name = origin.getName();
    result.price = origin.getPrice();
    result.timeCreated = origin.getTimeCreated();
    result.timeUpdated = origin.getTimeUpdated();;
    return result;
  }
}
