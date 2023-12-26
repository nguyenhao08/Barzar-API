package com.example.BarzarAPI.controllers;

import com.example.BarzarAPI.models.Category;
import com.example.BarzarAPI.models.Products;
// import com.example.BarzarAPI.models.Products;
import com.example.BarzarAPI.repositories.ProductsRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private ProductsRepository productsRepository;

  @GetMapping
  public List<Products> getAllProducts() {
    return productsRepository.findAll();
  }

}
