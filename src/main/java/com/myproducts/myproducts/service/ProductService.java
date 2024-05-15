package com.myproducts.myproducts.service;

import com.myproducts.myproducts.entity.ProductEntity;
import com.myproducts.myproducts.exception.ProductAlreadyExistsException;
import com.myproducts.myproducts.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProducts();

    ProductEntity findByTitle(String title) throws ProductNotFoundException;

    ProductEntity addProduct(ProductEntity product) throws ProductAlreadyExistsException;

    ProductEntity updateProduct(ProductEntity product) throws ProductNotFoundException;

    void deleteProduct(String title) throws ProductNotFoundException;

    List<ProductEntity> filterProducts(String title, BigDecimal minPrice, BigDecimal maxPrice);

    List<ProductEntity> sortProducts(List<ProductEntity> products, String sortBy);

    List<ProductEntity> searchProducts(String title, BigDecimal minPrice, BigDecimal maxPrice, String sortBy);
}
