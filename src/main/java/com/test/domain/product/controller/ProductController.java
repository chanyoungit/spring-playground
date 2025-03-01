package com.test.domain.product.controller;

import com.test.domain.product.dto.ProductReqDTO;
import com.test.domain.product.dto.ProductResDTO;
import com.test.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 물품 등록
    @PostMapping("/product")
    public ProductResDTO addProduct(@RequestBody ProductReqDTO request) {
        return productService.addProduct(request);
    }

    // 물품 검색
    @GetMapping("/search")
    public List<ProductResDTO> searchProduct(@RequestParam String keyword) {
        return productService.searchProduct(keyword);
    }

    // 엘라스틱서치로 검색
    @GetMapping("/elastic")
    public List<ProductResDTO> elasticProduct(@RequestParam String keyword) {
        return productService.searchProduct(keyword);
    }
}
