package com.example.productmanager.product.controller;

import com.example.productmanager.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 后端商品controller
 *
 * @Author: nansongling
 * @Date: 2021/10/8 4:33 PM
 **/
@Controller
public class ProductBackController {
  @PostMapping(value = "/manager/back/product/listProduct")
  public Response listProduct() {

    return null;
  }
}
