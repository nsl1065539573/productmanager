package com.example.productmanager.common;

import com.example.productmanager.utils.Response;
import com.example.productmanager.utils.exception.NslException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一包装controller的异常
 *
 * @Author: nansongling
 * @Date: 2021/9/17 6:33 PM
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(value = NslException.class)
  public Response<Object> businessExceptionHandler(HttpServletRequest httpServletRequest, NslException e) {
    logger.info("业务异常。code:" + e.getCode() + "msg:" + e.getDesc());
    return Response.genException(e.getCode(), e.getDesc());
  }
}
