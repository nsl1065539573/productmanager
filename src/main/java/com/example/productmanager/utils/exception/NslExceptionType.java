package com.example.productmanager.utils.exception;

public enum NslExceptionType {
  // 用户模块
  PARAM_ERROR("1001", "参数异常"),
  USER_NOT_EXIST("1002", "用户不存在"),
  PASSWORD_ERROR("1003", "密码错误"),
  ;

  public String code;
  public String desc;

  NslExceptionType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
