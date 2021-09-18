package com.example.productmanager.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: nansongling
 * @Date: 2021/9/17 7:23 PM
 **/
@SpringBootTest
public class DigestUtilTest {
  @Test
  public void testSHA2() {
    String password = "123456";
    System.out.println(DigestUtil.MD5(password));
  }
}
