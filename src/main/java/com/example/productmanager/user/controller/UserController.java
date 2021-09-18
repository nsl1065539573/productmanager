package com.example.productmanager.user.controller;

import com.example.productmanager.utils.Response;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 5:29 PM
 **/
@RestController(value = "/manager/user")
public class UserController {
  @PostMapping(value = "/test")
  public void test() {
    throw new NslException(NslExceptionType.PARAM_ERROR, "参数异常：%s", "啊啊啊");
  }

  @PostMapping(value = "/login")
  public Response<String> login() {
    return null;
  }
}
