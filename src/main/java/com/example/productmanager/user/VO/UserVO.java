package com.example.productmanager.user.VO;

import lombok.Data;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 7:01 PM
 **/
@Data
public class UserVO {
  private String username;
  private String password;
  private String nickname;

  public UserVO() {}

  public UserVO(String userName, String password, String nickname) {
    this.username = userName;
    this.password = password;
    this.nickname = nickname;
  }
}
