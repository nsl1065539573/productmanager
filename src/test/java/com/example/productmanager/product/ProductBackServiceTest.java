package com.example.productmanager.product;

import com.example.productmanager.BaseTest;
import com.example.productmanager.product.VO.ProductVo;
import com.example.productmanager.product.service.ProductBackService;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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
  }
}
