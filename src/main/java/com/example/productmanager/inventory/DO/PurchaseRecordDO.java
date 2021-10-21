package com.example.productmanager.inventory.DO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: nansongling
 * @Date: 2021/10/21 11:23 AM
 **/
@Data
public class PurchaseRecordDO {
  private Long id;
  private Long productId; // 商品id
  private String version; // 进货版本号
  private Long quantity; // 进货的数量
  private BigDecimal price; // 该类商品进货总价
  private Long timeCreated;
  private Long timeUpdated;

  public static PurchaseRecordDO from(InventoryDO origin) {
    PurchaseRecordDO res = new PurchaseRecordDO();
    res.productId = origin.getProductId();
    res.quantity = origin.getQuantity();
    res.price = origin.getProductPurchasingPrice();
    return res;
  }
}
