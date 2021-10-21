package com.example.productmanager.inventory.DO;

import com.example.productmanager.inventory.vo.InventoryVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: nansongling
 * @Date: 2021/10/18 4:55 PM
 **/
@Data
public class InventoryDO {
  private Long id;
  private Long productId; // 商品id
  private Long quantity; // 进货的数量
  private BigDecimal productPurchasingPrice; // 商品进价
  private Long timeCreated;
  private Long timeUpdated;
}
