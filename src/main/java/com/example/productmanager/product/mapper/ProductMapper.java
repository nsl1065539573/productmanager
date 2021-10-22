package com.example.productmanager.product.mapper;

import com.example.productmanager.product.DO.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
  List<ProductDO> listProduct();

  Integer addProduct(ProductDO productDO);

  ProductDO getProductByName(@Param("name") String name);

  List<ProductDO> fuzzyQueryProductByName(@Param("fuzzyName") String fuzzyName);

  List<ProductDO> findByNames(@Param("nameList") List<String> names);

  Integer update(ProductDO productDO);

  Integer delete(@Param("id") Long id);
}
