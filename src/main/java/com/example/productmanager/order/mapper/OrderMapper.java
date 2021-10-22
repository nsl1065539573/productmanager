package com.example.productmanager.order.mapper;

import com.example.productmanager.order.Do.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
  Long insert(OrderDO orderDO);
}
