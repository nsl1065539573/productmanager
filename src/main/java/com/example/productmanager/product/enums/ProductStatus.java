package com.example.productmanager.product.enums;

import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;

public enum ProductStatus {
  NORMAL("N", "正常状态"),
  DELETED("D", "已删除"),
  UN_PUBLISH("U", "已下架"),
  ;

  public String code;
  public String desc;

  ProductStatus(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static ProductStatus fromCodeOrException(String code) {
    for (ProductStatus status : ProductStatus.values()) {
      if (status.code.equals(code)) {
        return status;
      }
    }
    throw new NslException(NslExceptionType.PRODUCT_STATUS_CODE_UNKNOWN, "failed to convert from code to ProductStatus, code is %s", code);
  }
}
