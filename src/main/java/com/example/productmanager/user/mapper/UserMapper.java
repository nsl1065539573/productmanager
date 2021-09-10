package com.example.productmanager.user.mapper;

import com.example.productmanager.user.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
  Long insert(UserDO userDO);
}
