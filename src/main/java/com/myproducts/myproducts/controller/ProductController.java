package com.myproducts.myproducts.controller;


import com.myproducts.myproducts.entity.ProductEntity;
import com.myproducts.myproducts.exception.ProductAlreadyExistsException;
import com.myproducts.myproducts.exception.ProductNotFoundException;
import com.myproducts.myproducts.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Validated
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
//    private InMemoryProductServiceImpl service;

    private final ProductServiceImpl service;

    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        try {
            return ResponseEntity.ok(service.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity findByTitle(@PathVariable String title) {
        try {
            return ResponseEntity.ok(service.findByTitle(title));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid ProductEntity product) {
        try {
            service.addProduct(product);
            return ResponseEntity.ok("Товар добавлен");
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductEntity product) {
        try {
            service.updateProduct(product);
            return ResponseEntity.ok("Товар изменен");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity deleteProduct(@PathVariable String title) {
        try {
            service.deleteProduct(title);
            return ResponseEntity.ok("Товар удален");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/search")
    public ResponseEntity searchProducts(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "sortBy", required = false) String sortBy) {
        try {
            return ResponseEntity.ok(service.searchProducts(title, minPrice, maxPrice, sortBy));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
