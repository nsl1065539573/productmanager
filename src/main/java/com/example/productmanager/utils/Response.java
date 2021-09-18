package com.example.productmanager.utils;

import lombok.Data;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 5:29 PM
 **/
@Data
public class Response<T> {
  private static final String SUCCESS_CODE = "1000";

  private String code;
  private String desc;
  private T data;

  private Response(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private Response(T t) {
    this.data = t;
  }

  private Response(String code, String desc, T data) {
    this.code = code;
    this.desc = desc;
    this.data = data;
  }

  public static Response<Object> genException(String code, String desc) {
    return new Response<>(code, desc);
  }

  public static Response<Boolean> genSuccess() {
    return new Response<>(SUCCESS_CODE, "成功", Boolean.TRUE);
  }

  public static <T> Response<T> genResult(T t) {
    return new Response<>(SUCCESS_CODE, "成功", t);
  }
}
