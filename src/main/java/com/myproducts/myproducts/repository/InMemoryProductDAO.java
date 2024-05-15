package com.myproducts.myproducts.repository;

import com.myproducts.myproducts.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductDAO {
    private final List<ProductEntity> PRODUCTS = new ArrayList<>();

    public List<ProductEntity> getAllProducts() {
        return PRODUCTS;
    }

    public ProductEntity findByTitle(String title) {
        return PRODUCTS.stream()
                .filter((el -> el.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public ProductEntity addProduct(ProductEntity product) {
        PRODUCTS.add(product);
        return product;
    }

    public ProductEntity updateProduct(ProductEntity product) {
        int productIndex = IntStream.range(0, PRODUCTS.size())
                .filter(i -> PRODUCTS.get(i).getTitle().equals(product.getTitle()))
                .findFirst()
                .orElse(-1);
        if (productIndex > -1) {
            PRODUCTS.set(productIndex, product);
            return product;
        }
        return null;
    }

    public void deleteProduct(String title) {
        ProductEntity product = findByTitle(title);
        if (product != null) {
            PRODUCTS.remove(product);
        }
    }


}
