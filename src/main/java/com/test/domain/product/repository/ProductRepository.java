package com.test.domain.product.repository;

import com.test.domain.product.domain.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (p.title LIKE %:keyword% OR p.description LIKE %:keyword%) AND p.status = 'SELL'")
    List<Product> searchByKeywordAndStatus(@Param("keyword") String keyword);
}
