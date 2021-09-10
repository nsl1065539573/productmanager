package com.example.productmanager.user.service;

import com.example.productmanager.user.DO.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: nansongling
 * @Date: 2021/9/10 4:12 下午
 **/
@SpringBootTest
public class UserServiceTest {
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
}
