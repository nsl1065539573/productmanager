package com.example.productmanager.user.controller;

import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 5:29 PM
 **/
@RestController
public class UserController {
  @PostMapping(value = "/manager/user/test")
  public void test() {
    throw new NslException(NslExceptionType.PARAM_ERROR, "参数异常：%s", "啊啊啊");
  }
}
