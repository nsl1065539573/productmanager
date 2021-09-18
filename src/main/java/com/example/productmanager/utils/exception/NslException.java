package com.example.productmanager.utils.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 6:21 PM
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class NslException extends RuntimeException{
  private String code;
  private String desc;

  public NslException(NslExceptionType type, String format, Object... objs) {
    this.code = type.code;
    this.desc = String.format(format, objs);
  }

  public NslException(NslExceptionType type) {
    this.code = type.code;
    this.desc = type.desc;
  }
}
