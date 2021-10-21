package com.example.productmanager;

import com.example.productmanager.common.mapper.CommonMapper;
import com.example.productmanager.product.DO.ProductDO;
import com.example.productmanager.product.mapper.ProductMapper;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: nansongling
 * @Date: 2021/9/18 11:22 AM
 **/
@SpringBootTest
public class BaseTest {
  @Autowired
  private CommonMapper commonMapper;

  @Autowired
  protected ProductMapper productMapper;

  public String productName;
  public Long productId;

  @Test
  public void clearDB() {
    List<String> excludeTables = new ArrayList<String>() {{
      add("flyway_schema_history");
    }};
    List<String> tableNames = commonMapper.getAllTableNames(excludeTables);
    tableNames.forEach(n -> commonMapper.clearTable(n));
  }

  public void assertEquals(Object a, Object b) {
    if (!Objects.equals(a, b)) {
      throw new NslException(NslExceptionType.NOT_EQUALS, "expect is %s, current is %s", a, b);
    }
  }

  public void setProduct() {
    ProductDO productDO = new ProductDO();
    productDO.setName("testProduct");
    productDO.setPrice(BigDecimal.TEN);
    productMapper.addProduct(productDO);
    productDO = productMapper.getProductByName("testProduct");
    this.productId = productDO.getId();
    this.productName = productDO.getName();
  }
}
