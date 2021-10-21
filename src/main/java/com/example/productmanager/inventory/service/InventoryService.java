package com.example.productmanager.inventory.service;

import com.example.productmanager.inventory.DO.InventoryDO;
import com.example.productmanager.inventory.DO.PurchaseRecordDO;
import com.example.productmanager.inventory.mapper.InventoryMapper;
import com.example.productmanager.inventory.mapper.PurchaseRecordMapper;
import com.example.productmanager.inventory.vo.InventoryVO;
import com.example.productmanager.product.DO.ProductDO;
import com.example.productmanager.product.mapper.ProductMapper;
import com.example.productmanager.utils.DateUtils;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 库存service
 *
 * @Author: nansongling
 * @Date: 2021/10/13 10:31 AM
 **/
@Service
public class InventoryService {
  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private InventoryMapper inventoryMapper;

  @Autowired
  private PurchaseRecordMapper purchaseRecordMapper;

  /**
   * 进货
   * 根据商品名进货，传入vo的list，批量插入，如果没有的产品需要先创建
   */
  public void stock(List<InventoryVO> list) {
    // step1: 避免有重复商品的情况，合并一下
    Map<String, InventoryVO> map = list.stream().collect(Collectors.toMap(InventoryVO::getProductName, inventoryVO -> inventoryVO, (o, n) -> {
      o.setAmount(o.getAmount().add(n.getAmount()));
      o.setQuantity(o.getQuantity() + n.getQuantity());
      return o;
    }));
    // step2: 找到不存在的商品，要先创建商品
    List<String> nameList = new ArrayList<>(map.keySet());
    List<ProductDO> productDOList = productMapper.findByNames(nameList);
    List<String> existedProductNames = productDOList.stream().map(ProductDO::getName).collect(Collectors.toList());
    List<String> unExistProductNames = nameList.stream().filter(n -> !existedProductNames.contains(n)).collect(Collectors.toList());
    if (!CollectionUtils.isEmpty(unExistProductNames)) {
      throw new NslException(NslExceptionType.PRODUCT_NOT_EXIST, "以下商品不存在，请先创建商品: %s", unExistProductNames);
    }
    // step3: 根据VO构建DO
    Map<String, Long> nameToId = productDOList.stream().collect(Collectors.toMap(ProductDO::getName, ProductDO::getId));
    List<InventoryDO> inventoryDOList = genInventoryDOList(list, nameToId);
    List<PurchaseRecordDO> purchaseRecordDOList = inventoryDOList.stream().map(i -> {
      PurchaseRecordDO res = PurchaseRecordDO.from(i);
      res.setVersion(DateUtils.getDateStr(DateUtils.now(), DateUtils.DATE_FORMAT));
      return res;
    }).collect(Collectors.toList());
    // step4: 插入库存记录和进货记录
    insertInventoriesAndPurchase(inventoryDOList, purchaseRecordDOList);
  }

  private List<InventoryDO> genInventoryDOList(List<InventoryVO> origin, Map<String, Long> productNameToId) {
    return origin.stream().map(v -> {
      InventoryDO res = new InventoryDO();
      res.setProductId(productNameToId.get(v.getProductName()));
      res.setQuantity(v.getQuantity());
      res.setProductPurchasingPrice(v.getAmount());
      return res;
    }).collect(Collectors.toList());
  }

  /**
   * 插入商品以及进货信息
   */
  @Transactional
  protected void insertInventoriesAndPurchase(List<InventoryDO> inventoryDOList, List<PurchaseRecordDO> purchaseRecordDOList) {
    inventoryMapper.batchInsert(inventoryDOList);
    purchaseRecordMapper.batchInsert(purchaseRecordDOList);
  }

}
