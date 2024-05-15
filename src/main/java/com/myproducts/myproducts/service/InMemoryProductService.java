package com.myproducts.myproducts.service;

import com.myproducts.myproducts.entity.ProductEntity;
import com.myproducts.myproducts.exception.ProductAlreadyExistsException;
import com.myproducts.myproducts.exception.ProductNotFoundException;

import java.util.List;

public interface InMemoryProductService {
    List<ProductEntity> getAllProducts();

    ProductEntity findByTitle(String title) throws ProductNotFoundException;

    ProductEntity addProduct(ProductEntity product) throws ProductAlreadyExistsException;

    ProductEntity updateProduct(ProductEntity product) throws ProductNotFoundException;

    void deleteProduct(String title) throws ProductNotFoundException;
}
