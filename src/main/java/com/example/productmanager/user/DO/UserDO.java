package com.example.productmanager.user.DO;

import com.example.productmanager.user.VO.UserVO;
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

  public static UserDO from(UserVO userVO) {
    UserDO res = new UserDO();
    res.username = userVO.getUserName();
    res.password = userVO.getPassword();
    res.name = userVO.getNickname();
    return res;
  }
}
