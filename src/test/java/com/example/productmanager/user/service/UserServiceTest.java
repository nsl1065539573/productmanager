package com.example.productmanager.user.service;

import com.example.productmanager.BaseTest;
import com.example.productmanager.ProductmanagerApplication;
import com.example.productmanager.user.DO.UserDO;
import com.example.productmanager.user.VO.UserVO;
import com.example.productmanager.user.mapper.UserMapper;
import com.example.productmanager.utils.DigestUtil;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: nansongling
 * @Date: 2021/9/10 4:12 下午
 **/
public class UserServiceTest extends BaseTest {
  @Autowired
  private UserService userService;

  @Test
  public void testInsert() {
    UserDO userDO = new UserDO();
    userDO.setUsername("张三");
    userDO.setPassword("asasfadfas");
    userDO.setName("啊啊");
    userDO.setTimeCreated(System.currentTimeMillis());
    userDO.setTimeUpdated(System.currentTimeMillis());
    userService.insert(userDO);
  }

  @Test
  @DisplayName("注册测试登录")
  public void testLogin() {
    UserVO user = new UserVO("abcd", "123456", "张三");
    userService.register(user);
    UserVO userVO = new UserVO();
    userVO.setUserName("abcd");
    userVO.setPassword("123456");
    String token = userService.login(userVO);
    if (token == null) {
      throw new NslException(NslExceptionType.PARAM_ERROR, "token为空");
    }
    userVO.setPassword("2131");
    try {
      userService.login(userVO);
    } catch (NslException e) {
      if (!e.getCode().equals(NslExceptionType.PASSWORD_ERROR.code)) {
        throw new NslException(NslExceptionType.PARAM_ERROR, "密码应该校验失败");
      }
    }
    System.out.println(token);
  }

  @BeforeEach
  private void cleanUser() {
    clearDB();
  }
}
