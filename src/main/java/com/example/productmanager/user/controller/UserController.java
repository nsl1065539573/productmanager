package com.example.productmanager.user.controller;

import com.example.productmanager.user.VO.UserVO;
import com.example.productmanager.user.service.UserService;
import com.example.productmanager.utils.Response;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 5:29 PM
 **/
@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping(value = "/manager/user/test")
  public void test() {
    throw new NslException(NslExceptionType.PARAM_ERROR, "参数异常：%s", "啊啊啊");
  }

  @PostMapping(value = "/manager/user/login")
  public Response<String> login(@RequestBody UserVO userVO) {
    String token = userService.login(userVO);
    return Response.genResult(token);
  }

  @PostMapping(value = "/manager/user/register")
  public Response<Boolean> register(@RequestBody UserVO userVO) {
    userService.register(userVO);
    return Response.genSuccess();
  }
}
