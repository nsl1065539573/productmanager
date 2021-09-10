package com.example.productmanager.user.service;

import com.example.productmanager.user.DO.UserDO;
import com.example.productmanager.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: nansongling
 * @Date: 2021/9/10 4:14 下午
 **/
@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  public void insert(UserDO userDO) {
    System.out.println("insert...");
    userMapper.insert(userDO);
    System.out.println("insert success...");
  }
}
