package com.test.domain.product.dto;

public record ProductReqDTO(
        Long userId,
        String title,
        String description,
        Long price
) {}