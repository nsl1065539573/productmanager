package com.example.productmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProductmanagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductmanagerApplication.class, args);
  }

}
