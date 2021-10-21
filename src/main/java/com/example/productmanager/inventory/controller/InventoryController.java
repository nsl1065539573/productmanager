package com.example.productmanager.inventory.controller;

import com.example.productmanager.inventory.service.InventoryService;
import com.example.productmanager.inventory.vo.InventoryVO;
import com.example.productmanager.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: nansongling
 * @Date: 2021/10/21 7:08 PM
 **/
@Controller
public class InventoryController {
  @Autowired
  private InventoryService inventoryService;

  @PostMapping("/manager/back/inventory/stock")
  public Response<Boolean> stock(@RequestBody List<InventoryVO> list) {
    inventoryService.stock(list);
    return Response.genSuccess();
  }
}
