package com.example.productmanager.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommonMapper {
  List<String> getAllTableNames(@Param("excludeList") List<String> excludeList);

  void clearTable(@Param("tableName") String tableName);
}
