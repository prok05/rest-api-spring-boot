package com.myproducts.myproducts.service.impl;

import com.myproducts.myproducts.entity.ProductEntity;
import com.myproducts.myproducts.exception.ProductAlreadyExistsException;
import com.myproducts.myproducts.exception.ProductNotFoundException;
import com.myproducts.myproducts.repository.ProductRepository;
import com.myproducts.myproducts.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return repository.findAll();
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
        return repository.save(product);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product) throws ProductNotFoundException {
        if (repository.findByTitle(product.getTitle()) == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        return repository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(String title) throws ProductNotFoundException {
        if (repository.findByTitle(title) == null) {
            throw new ProductNotFoundException("Товар не найден");
        }
        repository.deleteByTitle(title);
    }

    @Override
    public List<ProductEntity> filterProducts(String title, BigDecimal minPrice, BigDecimal maxPrice) {
        List<ProductEntity> products = repository.findAll();
        if (title != null) {
            products = products.stream()
                    .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (minPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice().compareTo(minPrice) >= 0)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice().compareTo(maxPrice) <= 0)
                    .collect(Collectors.toList());
        }
        return products;
    }

    @Override
    public List<ProductEntity> sortProducts(List<ProductEntity> products, String sortBy) {
        if (sortBy != null) {
            return products.stream()
                    .sorted((p1, p2) -> {
                        if ("title".equals(sortBy)) {
                            return p1.getTitle().compareToIgnoreCase(p2.getTitle());
                        } else if ("price".equals(sortBy)) {
                            return p1.getPrice().compareTo(p2.getPrice());
                        }
                        return 0;
                    })
                    .collect(Collectors.toList());
        }
        return products;
    }

    @Override
    public List<ProductEntity> searchProducts(String title, BigDecimal minPrice, BigDecimal maxPrice, String sortBy) {
        List<ProductEntity> filteredProducts = filterProducts(title, minPrice, maxPrice);
        return sortProducts(filteredProducts, sortBy);
    }
}
