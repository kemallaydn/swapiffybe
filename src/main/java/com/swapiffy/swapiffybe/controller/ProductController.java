package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dto.LoginForm;
import com.swapiffy.swapiffybe.dto.LoginResponse;
import com.swapiffy.swapiffybe.entity.Product;
import com.swapiffy.swapiffybe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    ProductService productService = new ProductService();
    @GetMapping("/all")
    public List<Product> login() {
        return productService.getAll();
    }

    @GetMapping("/get")
    public Product getProduct(@RequestParam Long id) {
        return productService.FindById(id);
    }
}
