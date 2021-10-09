package com.example.productmanager.product.service;

import com.example.productmanager.product.DO.ProductDO;
import com.example.productmanager.product.VO.ProductVo;
import com.example.productmanager.product.mapper.ProductMapper;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后端商品service
 *
 * @Author: nansongling
 * @Date: 2021/10/8 4:56 PM
 **/
@Service
public class ProductBackService {
  @Autowired
  private ProductMapper productMapper;

  public List<ProductVo> listProduct() {
    List<ProductDO> productDOS = productMapper.listProduct();
    return productDOS.stream().map(ProductVo::from).collect(Collectors.toList());
  }

  /**
   * 新增商品
   */
  public void addProduct(ProductVo origin) {
    ProductDO existed = productMapper.getProductByName(origin.getName());
    if (existed != null) {
      throw new NslException(NslExceptionType.PARAM_ERROR, "商品名称已存在");
    }
    Integer res = productMapper.addProduct(ProductDO.from(origin));
    if (res != 1) {
      throw new NslException(NslExceptionType.UNKNOWN_ERROR, "插入失败，请联系开发人员");
    }
  }

  /**
   * 根据商品名模糊查询
   */
  public List<ProductVo> fuzzyQueryProductByName(String fuzzyName) {
    List<ProductDO> productDOS = productMapper.fuzzyQueryProductByName(fuzzyName);
    return productDOS.stream().map(ProductVo::from).collect(Collectors.toList());
  }
}
