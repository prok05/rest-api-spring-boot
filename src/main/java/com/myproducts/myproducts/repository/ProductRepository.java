package com.myproducts.myproducts.repository;

import com.myproducts.myproducts.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByTitle(String title);
    ProductEntity findByTitle(String title);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title);

    List<ProductEntity> findByPriceGreaterThanEqual(BigDecimal price);
    List<ProductEntity> findByPriceLessThanEqual(BigDecimal price);


}
