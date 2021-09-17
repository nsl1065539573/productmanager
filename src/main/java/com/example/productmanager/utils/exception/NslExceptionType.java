package com.example.productmanager.utils.exception;

public enum NslExceptionType {
  PARAM_ERROR("1001", "参数异常");
  ;

  public String code;
  public String desc;

  NslExceptionType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
