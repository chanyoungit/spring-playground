package com.test.domain.product.dto;

import com.test.domain.product.domain.Product;
import com.test.domain.product.domain.ProductStatus;

public record ProductResDTO(
        Long id,
        String title,
        String description,
        Long price,
        ProductStatus status
) {
    public static ProductResDTO fromEntity(Product product) {
        return new ProductResDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getStatus()
        );
    }

    public static ProductResDTO fromDocument(ProductDocument productDocument) {
        return new ProductResDTO(
                Long.parseLong(productDocument.getId()),
                productDocument.getTitle(),
                productDocument.getDescription(),
                productDocument.getPrice(),
                ProductStatus.SELL

        );
    }
}