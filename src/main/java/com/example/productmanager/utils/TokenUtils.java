package com.example.productmanager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: nansongling
 * @Date: 2021/9/9 7:25 下午
 **/
public class TokenUtils {
  //设置过期时间
  private static final long EXPIRE_DATE = 30 * 60 * 100000;
  //token秘钥
  private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

  public static String genToken(String username, String password) {
    String token = "";
    try {
      // 设置过期时间
      Date expireTime = new Date(System.currentTimeMillis() + EXPIRE_DATE);
      //秘钥及加密算法
      Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
      //设置头部信息
      Map<String, Object> header = new HashMap<>();
      header.put("typ", "JWT");
      header.put("alg", "HS256");
      //携带username，password信息，生成签名
      token = JWT.create()
          .withHeader(header)
          .withClaim("username", username)
          .withClaim("password", password).withExpiresAt(expireTime)
          .sign(algorithm);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return token;
  }

  public static boolean verify(String token){
    /**
     * @desc   验证token，通过返回true
     * @create 2019/1/18/018 9:39
     * @params [token]需要校验的串
     **/
    try {
      Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT jwt = verifier.verify(token);
      System.out.println(jwt);
      return true;
    }catch (Exception e){
      e.printStackTrace();
      return  false;
    }
  }

  public static void main(String[] args) {
    String username ="zhangsan";
    String password = "123";
    String token = genToken(username,password);
    System.out.println(token);
    boolean b = verify(token);
    System.out.println(b);
  }
}
