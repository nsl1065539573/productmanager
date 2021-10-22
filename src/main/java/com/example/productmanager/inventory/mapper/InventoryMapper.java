package com.example.productmanager.inventory.mapper;

import com.example.productmanager.inventory.DO.InventoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface InventoryMapper {
  void batchInsert(@Param("inventoryDOS") List<InventoryDO> inventoryDOS);

  List<InventoryDO> listAll();

  List<InventoryDO> listByProductIds(@Param("productIds") List<Long> productIds);

  Integer updateInventory(InventoryDO inventoryDO);

  Integer batchUpdateInventory(@Param("list") List<InventoryDO> list);
}
