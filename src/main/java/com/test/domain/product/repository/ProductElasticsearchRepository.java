package com.test.domain.product.repository;

import com.test.domain.product.dto.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<ProductDocument, String> {
    List<ProductDocument> findByTitleContainingOrDescriptionContainingAndStatus(
            String title, String description, String status);
}
