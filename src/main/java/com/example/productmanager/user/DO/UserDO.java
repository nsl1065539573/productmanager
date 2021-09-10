package com.example.productmanager.user.DO;

import lombok.Data;

/**
 * @Author: nansongling
 * @Date: 2021/9/10 4:01 下午
 **/
@Data
public class UserDO {
  private Long id;
  private String username;
  private String password;
  private String name;
  private Long timeCreated;
  private Long timeUpdated;
}
