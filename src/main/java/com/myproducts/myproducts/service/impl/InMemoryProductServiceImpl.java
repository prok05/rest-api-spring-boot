package com.myproducts.myproducts.service.impl;

import com.myproducts.myproducts.entity.ProductEntity;
import com.myproducts.myproducts.exception.ProductAlreadyExistsException;
import com.myproducts.myproducts.exception.ProductNotFoundException;
import com.myproducts.myproducts.repository.InMemoryProductDAO;
import com.myproducts.myproducts.service.InMemoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryProductServiceImpl implements InMemoryProductService {
    @Autowired
    private InMemoryProductDAO repository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public ProductEntity findByTitle(String title) throws ProductNotFoundException {
        if (repository.findByTitle(title) == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        return repository.findByTitle(title);
    }

    @Override
    public ProductEntity addProduct(ProductEntity product) throws ProductAlreadyExistsException {
        if (repository.findByTitle(product.getTitle()) != null) {
            throw new ProductAlreadyExistsException("Товар с таким названием уже существует");
        }
        return repository.addProduct(product);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product) throws ProductNotFoundException {
        if (repository.findByTitle(product.getTitle()) == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        return repository.updateProduct(product);
    }

    @Override
    public void deleteProduct(String title) throws ProductNotFoundException {
        if (repository.findByTitle(title) == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        repository.deleteProduct(title);
    }
}
