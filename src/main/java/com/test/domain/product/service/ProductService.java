package com.test.domain.product.service;

import com.test.domain.product.domain.Product;
import com.test.domain.product.domain.ProductStatus;
import com.test.domain.product.dto.ProductDocument;
import com.test.domain.product.dto.ProductReqDTO;
import com.test.domain.product.dto.ProductResDTO;
import com.test.domain.product.repository.ProductElasticsearchRepository;
import com.test.domain.product.repository.ProductRepository;
import com.test.domain.user.domain.User;
import com.test.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;
    private final UserRepository userRepository;

    public ProductResDTO addProduct(ProductReqDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.userId()));

        Product product = Product.builder()
                .seller(user)
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .status(ProductStatus.SELL)
                .build();

        productRepository.save(product);

        ProductDocument productDocument = ProductDocument.fromEntity(product);
        productElasticsearchRepository.save(productDocument);

        return new ProductResDTO(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getStatus());
    }

    public List<ProductResDTO> searchProduct(String keyword) {
        List<Product> products = productRepository.searchByKeywordAndStatus(keyword);
        return products.stream()
                .map(ProductResDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ProductResDTO> elasticSearch(String keyword) {
        List<ProductDocument> products = productElasticsearchRepository
                .findByTitleContainingOrDescriptionContainingAndStatus(keyword, keyword, "SELL");

        return products.stream()
                .map(ProductResDTO::fromDocument)
                .collect(Collectors.toList());
    }
}
