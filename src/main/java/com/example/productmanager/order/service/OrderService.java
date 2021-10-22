package com.example.productmanager.order.service;

import com.example.productmanager.inventory.DO.InventoryDO;
import com.example.productmanager.inventory.mapper.InventoryMapper;
import com.example.productmanager.order.Do.OrderDO;
import com.example.productmanager.order.Do.OrderDetailsDO;
import com.example.productmanager.order.mapper.OrderDetailsMapper;
import com.example.productmanager.order.mapper.OrderMapper;
import com.example.productmanager.order.vo.OrderDetailsVO;
import com.example.productmanager.product.mapper.ProductMapper;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: nansongling
 * @Date: 2021/10/22 4:09 PM
 **/
@Service
public class OrderService {
  private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private InventoryMapper inventoryMapper;

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private OrderDetailsMapper orderDetailsMapper;

  @Transactional
  public void createOrder(List<OrderDetailsVO> detailsVOList) {
    // step1: 根据商品id获得商品id与库存的map
    List<Long> productIdList = detailsVOList.stream().map(OrderDetailsVO::getProductId).collect(Collectors.toList());
    // 根据时间排序且库存大于0的
    List<InventoryDO> inventoryDOS = inventoryMapper.listByProductIds(productIdList);
    Map<Long, List<InventoryDO>> map = inventoryDOS.stream().collect(Collectors.toMap(InventoryDO::getProductId, i -> {
      List<InventoryDO> temp = new LinkedList<>();
      temp.add(i);
      return temp;
    }, (o, n) -> {
      o.addAll(n);
      return o;
    }));
    // step2: 检查库存是否足够
    Map<Long, Long> productIdToQuantity = inventoryDOS.stream().collect(Collectors.toMap(InventoryDO::getProductId, InventoryDO::getQuantity, Long::sum));
    detailsVOList.forEach(d -> {
      if (d.getQuantity() > productIdToQuantity.get(d.getProductId())) {
        throw new NslException(NslExceptionType.INSUFFICIENT_INVENTORY, "%s 商品库存不足", d.getProductId());
      }
    });
    // step3: 扣库存
    BigDecimal totalPrices = BigDecimal.ZERO;
    List<InventoryDO> inventoryDOList = new LinkedList<>();
    for (OrderDetailsVO vo : detailsVOList) {
      Integer quantity = vo.getQuantity();
      totalPrices = totalPrices.add(vo.getAmount().multiply(BigDecimal.valueOf(vo.getQuantity())));
      int index = 0;
      while (quantity > 0) {
        if (index >= map.get(vo.getProductId()).size()) {
          logger.error("商品库存不足，OrderDetailsVO is : " + vo.toString());
          throw new NslException(NslExceptionType.INSUFFICIENT_INVENTORY, "商品库存不足，id = " + vo.getProductId());
        }
        InventoryDO inventoryDO = map.get(vo.getProductId()).get(index);
        if (inventoryDO.getQuantity() > quantity) {
          inventoryDO.setQuantity(inventoryDO.getQuantity() - quantity);
          quantity = 0;
        } else {
          quantity = Math.toIntExact(quantity - inventoryDO.getQuantity());
          index++;
          inventoryDO.setQuantity(0L);
        }
        inventoryDOList.add(inventoryDO);
//        inventoryMapper.updateInventory(inventoryDO);
      }
    }
    inventoryMapper.batchUpdateInventory(inventoryDOList);
    // step4: 创建订单表记录
    OrderDO orderDO = new OrderDO(totalPrices);
    Long orderId = orderMapper.insert(orderDO);
    if (orderId == null || orderId < 1L) {
      logger.error("insert into order failed");
      throw new NslException(NslExceptionType.UNKNOWN_ERROR, "创建订单失败，请联系开发人员");
    }
    // step5: 创建订单明细表的记录
    List<OrderDetailsDO> orderDetailsDOS = detailsVOList.stream().map(d -> {
      OrderDetailsDO res = OrderDetailsDO.from(d);
      res.setOrderId(orderId);
      return res;
    }).collect(Collectors.toList());
    Integer res = orderDetailsMapper.batchInsert(orderDetailsDOS);
    if (res < orderDetailsDOS.size()) {
      throw new NslException(NslExceptionType.UNKNOWN_ERROR, "创建订单失败，请联系开发人员");
    }
  }
}
