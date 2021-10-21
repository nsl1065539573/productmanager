package com.example.productmanager.inventory;

import com.example.productmanager.BaseTest;
import com.example.productmanager.inventory.DO.InventoryDO;
import com.example.productmanager.inventory.DO.PurchaseRecordDO;
import com.example.productmanager.inventory.mapper.InventoryMapper;
import com.example.productmanager.inventory.mapper.PurchaseRecordMapper;
import com.example.productmanager.inventory.service.InventoryService;
import com.example.productmanager.inventory.vo.InventoryVO;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @Author: nansongling
 * @Date: 2021/10/21 7:15 PM
 **/
public class InventoryServiceTest extends BaseTest {
  @Autowired
  private InventoryService inventoryService;

  @Autowired
  private InventoryMapper inventoryMapper;

  @Autowired
  private PurchaseRecordMapper purchaseRecordMapper;

  @BeforeEach
  public void setUp() {
    super.clearDB();
    super.setProduct();
  }

  @Test
  public void testStock() {
    InventoryVO vo = new InventoryVO();
    vo.setProductName(super.productName);
    vo.setQuantity(100L);
    vo.setAmount(BigDecimal.valueOf(1000));
    List<InventoryVO> list = Collections.singletonList(vo);
    inventoryService.stock(list);
    List<InventoryDO> inventoryDOList = inventoryMapper.listAll();
    assertEquals(inventoryDOList.size(), 1);
    List<PurchaseRecordDO> purchaseRecordDOList = purchaseRecordMapper.listAll();
    assertEquals(purchaseRecordDOList.size(), 1);
    // 测试插入一个不存在的商品
    String unExistName = "test";
    vo.setProductName(unExistName);
    try {
      inventoryService.stock(list);
    } catch (NslException e) {
      assertEquals(e.getCode(), NslExceptionType.PRODUCT_NOT_EXIST.code);
    }
  }
}
