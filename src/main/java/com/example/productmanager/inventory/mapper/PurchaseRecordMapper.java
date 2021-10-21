package com.example.productmanager.inventory.mapper;

import com.example.productmanager.inventory.DO.PurchaseRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PurchaseRecordMapper {
  void batchInsert(@Param("list") List<PurchaseRecordDO> list);

  List<PurchaseRecordDO> listAll();
}
