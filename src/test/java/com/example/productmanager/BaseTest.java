package com.example.productmanager;

import com.example.productmanager.common.mapper.CommonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nansongling
 * @Date: 2021/9/18 11:22 AM
 **/
@SpringBootTest
public class BaseTest {
  @Autowired
  private CommonMapper commonMapper;

  @Test
  public void clearDB() {
    List<String> excludeTables = new ArrayList<String>() {{
      add("flyway_schema_history");
    }};
    List<String> tableNames = commonMapper.getAllTableNames(excludeTables);
    tableNames.forEach(n -> commonMapper.clearTable(n));
  }
}
