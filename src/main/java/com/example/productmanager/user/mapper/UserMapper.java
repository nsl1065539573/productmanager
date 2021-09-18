package com.example.productmanager.user.mapper;

import com.example.productmanager.user.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
  Long insert(UserDO userDO);

  Long deleteAll();

  UserDO getByUsername(@Param("username") String username);

  UserDO queryByUsernameAndPassword(UserDO userDO);
}
