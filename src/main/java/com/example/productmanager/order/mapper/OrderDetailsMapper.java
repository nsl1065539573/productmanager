package com.example.productmanager.order.mapper;

import com.example.productmanager.order.Do.OrderDetailsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDetailsMapper {
  Integer batchInsert(@Param("list") List<OrderDetailsDO> orderDetailsDOS);
}
