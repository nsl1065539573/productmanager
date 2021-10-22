package com.example.productmanager.order;

import com.example.productmanager.BaseTest;
import com.example.productmanager.inventory.DO.InventoryDO;
import com.example.productmanager.inventory.mapper.InventoryMapper;
import com.example.productmanager.inventory.service.InventoryService;
import com.example.productmanager.inventory.vo.InventoryVO;
import com.example.productmanager.order.service.OrderService;
import com.example.productmanager.order.vo.OrderDetailsVO;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: nansongling
 * @Date: 2021/10/22 5:20 PM
 **/
public class OrderServiceTest extends BaseTest {
  @Autowired
  private OrderService orderService;

  @Autowired
  private InventoryService inventoryService;

  @Autowired
  private InventoryMapper inventoryMapper;

  @BeforeEach
  public void setUp() {
    super.clearDB();
    super.setProduct();
    super.setProduct1();
  }

  @Test
  public void testCreateOrder() {
    OrderDetailsVO vo1 = new OrderDetailsVO();
    vo1.setProductId(productId);
    vo1.setAmount(BigDecimal.valueOf(20));
    vo1.setQuantity(10);
    OrderDetailsVO vo2 = new OrderDetailsVO();
    vo2.setProductId(productId1);
    vo2.setQuantity(10);
    vo2.setAmount(BigDecimal.valueOf(20));
    // 测一下库存足够的情况
    InventoryVO inventoryVO = new InventoryVO(productName, 9L, BigDecimal.valueOf(5000));
    InventoryVO inventoryVO1 = new InventoryVO(productName1, 9L, BigDecimal.valueOf(5000));
    inventoryService.stock(Arrays.asList(inventoryVO, inventoryVO1));
    inventoryVO = new InventoryVO(productName, 10L, BigDecimal.valueOf(5000));
    inventoryVO1 = new InventoryVO(productName1, 10L, BigDecimal.valueOf(5000));
    inventoryService.stock(Arrays.asList(inventoryVO, inventoryVO1));
    orderService.createOrder(Arrays.asList(vo1, vo2));
    List<InventoryDO> inventoryDOS = inventoryMapper.listByProductIds(Arrays.asList(productId, productId1));
    Map<Long, List<InventoryDO>> map = inventoryDOS.stream().collect(Collectors.toMap(InventoryDO::getProductId, i -> new LinkedList<InventoryDO>() {{
      add(i);
    }}, (o, n) -> {
      o.addAll(n);
      return o;
    }));
    List<InventoryDO> inventoryDOList = map.get(productId);
    assertEquals(inventoryDOList.get(0).getQuantity(), 9L);
    inventoryDOList = map.get(productId1);
    assertEquals(inventoryDOList.get(0).getQuantity(), 9L);
    try {
      orderService.createOrder(Arrays.asList(vo1, vo2));
    } catch (NslException e) {
      assertEquals(e.getCode(), NslExceptionType.INSUFFICIENT_INVENTORY.code);
    }
  }
}
