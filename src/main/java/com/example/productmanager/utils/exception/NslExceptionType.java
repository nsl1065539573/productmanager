package com.example.productmanager.utils.exception;

public enum NslExceptionType {
  // 用户模块
  PARAM_ERROR("1001", "参数异常"),
  USER_NOT_EXIST("1002", "用户不存在"),
  PASSWORD_ERROR("1003", "密码错误"),
  USER_ALREADY_EXIST("1004", "用户已存在"),

  // 库存模块
  PRODUCT_NOT_EXIST("2001", "商品不存在"),

  // UT模块
  NOT_EQUALS("2001", "结果与预想值不同"),
  UNKNOWN_ERROR("9999", "未知异常"),
  ;

  public String code;
  public String desc;

  NslExceptionType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
