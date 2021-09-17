package com.example.productmanager.utils;

import lombok.Data;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 5:29 PM
 **/
@Data
public class Response<T> {
  private String code;
  private String desc;
  private T data;

  private Response(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Response<Object> genException(String code, String desc) {
    return new Response<>(code, desc);
  }
}
