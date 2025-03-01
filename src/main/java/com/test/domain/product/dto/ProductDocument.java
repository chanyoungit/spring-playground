package com.test.domain.product.dto;

import com.test.domain.product.domain.Product;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
public class ProductDocument {

    @Id
    private String id;

    private String title;
    private String description;
    private String status;
    private Long price;

    public static ProductDocument fromEntity(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .title(product.getTitle())
                .description(product.getDescription())
                .status(product.getStatus().name())
                .price(product.getPrice())
                .build();
    }
}
