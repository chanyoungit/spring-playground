package com.test.domain.product.domain;

import com.test.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "product",
        indexes = {
                @Index(name = "idx_product_title", columnList = "title"),
                @Index(name = "idx_product_description", columnList = "description"),
                @Index(name = "idx_product_status", columnList = "status")
        }
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Setter
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "seller_Id")
    private User seller;

    private String title;
    private String description;
    private Long price;

    public void updateProduct(String title, String description, Long price){
        this.title = title;
        this.description = description;
        this.price = price;
    }
}