package com.example.productmanager.product;

import com.example.productmanager.BaseTest;
import com.example.productmanager.product.DO.ProductDO;
import com.example.productmanager.product.VO.ProductVo;
import com.example.productmanager.product.service.ProductBackService;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Author: nansongling
 * @Date: 2021/10/8 6:46 PM
 **/
public class ProductBackServiceTest extends BaseTest {
  @Autowired
  private ProductBackService productBackService;

  @Test
  public void testInsert() {
    ProductVo productVo = new ProductVo();
    productVo.setName("Z1型水泵");
    productVo.setPrice(BigDecimal.TEN);
    productBackService.addProduct(productVo);
    ProductVo productVo1 = new ProductVo();
    productVo1.setName("Z1型水泵");
    productVo1.setPrice(BigDecimal.TEN);
    try {
      productBackService.addProduct(productVo1);
    } catch (NslException nslException) {
      if (!NslExceptionType.PARAM_ERROR.code.equals(nslException.getCode())) {
        throw new NslException(NslExceptionType.PARAM_ERROR, "这里应该报这个错");
      }
    }
    List<ProductDO> productDOS = productMapper.fuzzyQueryProductByName("Z1型水泵");
    productBackService.deleteProduct(productDOS.get(0).getId());
    productBackService.addProduct(productVo1);
  }

  @Test
  public void testQuery() {
    ProductVo productVo = new ProductVo();
    productVo.setName("Z1型水泵");
    productVo.setPrice(BigDecimal.TEN);
    productBackService.addProduct(productVo);
    List<ProductVo> productVos = productBackService.listProduct();
    assertEquals(1, productVos.size());
  }

  @Test
  @DisplayName("测试根据商品名模糊查询")
  public void testFuzzyQuery() {
    ProductVo productVo = new ProductVo();
    productVo.setName("Z1型水泵");
    productVo.setPrice(BigDecimal.TEN);
    productBackService.addProduct(productVo);
    ProductVo productVo1 = new ProductVo();
    productVo1.setName("Z2型水泵");
    productVo1.setPrice(BigDecimal.TEN);
    productBackService.addProduct(productVo1);
    List<ProductVo> res = productBackService.fuzzyQueryProductByName("水泵");
    assertEquals(res.size(), 2);
  }

  @BeforeEach
  private void setUp() {
    clearDB();
  }
}
