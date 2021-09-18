package com.example.productmanager.user.service;

import com.example.productmanager.user.DO.UserDO;
import com.example.productmanager.user.VO.UserVO;
import com.example.productmanager.user.mapper.UserMapper;
import com.example.productmanager.utils.DigestUtil;
import com.example.productmanager.utils.TokenUtils;
import com.example.productmanager.utils.exception.NslException;
import com.example.productmanager.utils.exception.NslExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

/**
 * @Author: nansongling
 * @Date: 2021/9/10 4:14 下午
 **/
@Service
public class UserService {
  private final static Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserMapper userMapper;

  public void insert(UserDO userDO) {
    System.out.println("insert...");
    logger.info("insert...{}", userDO);
    userMapper.insert(userDO);
    System.out.println("insert success...");
  }

  public String login(UserVO userVO) {
    String username = userVO.getUsername();
    String password = userVO.getPassword();
    if (StringUtils.isEmpty(username) || password.isEmpty()) {
      throw new NslException(NslExceptionType.PARAM_ERROR, "username or password is null, username: %s, password: %s", StringUtils.isEmpty(username), StringUtils.isEmpty(password));
    }
    String passwordMD5 = DigestUtil.MD5(password);
    UserDO user = userMapper.getByUsername(username);
    if (user == null) {
      throw new NslException(NslExceptionType.USER_NOT_EXIST);
    }
    userVO.setPassword(passwordMD5);
    UserDO userDO = userMapper.queryByUsernameAndPassword(UserDO.from(userVO));
    if (userDO == null) {
      throw new NslException(NslExceptionType.PASSWORD_ERROR);
    }
    return TokenUtils.genToken(username, passwordMD5);
  }

  public void register(UserVO userVO) {
    if (userVO == null || StringUtils.isEmpty(userVO.getUsername()) || StringUtils.isEmpty(userVO.getPassword()) || StringUtils.isEmpty(userVO.getNickname())) {
      throw new NslException(NslExceptionType.PARAM_ERROR, "必填字段为空");
    }
    UserDO user = userMapper.getByUsername(userVO.getUsername());
    if (user != null) {
      throw new NslException(NslExceptionType.USER_ALREADY_EXIST, "用户已存在");
    }
    userVO.setPassword(DigestUtil.MD5(userVO.getPassword()));
    userMapper.insert(UserDO.from(userVO));
  }
}
