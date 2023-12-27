package com.example.BarzarAPI.controllers;

import com.example.BarzarAPI.models.Category;
import com.example.BarzarAPI.models.OrderProducts;
import com.example.BarzarAPI.repositories.OrdersProductsRepository;
// import com.example.BarzarAPI.models.Products;
import com.example.BarzarAPI.repositories.ProductsRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrdersProductsController {
  @Autowired
  private OrdersProductsRepository ordersProductsRepository;

  @GetMapping
  public List<OrderProducts> getAllCategories() {
    return ordersProductsRepository.findAll();
  }

  @PostMapping
  public OrderProducts createCategory(@RequestBody OrderProducts orderProducts, HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    return ordersProductsRepository.save(orderProducts);
  }
}
