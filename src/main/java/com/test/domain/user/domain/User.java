package com.test.domain.user.domain;

import com.test.domain.post.domain.Post;
import com.test.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Setter
    private Long id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @OneToMany(mappedBy = "seller")
    private List<Product> sellingProducts = new ArrayList<>();

    @Builder
    public User(String nickname) {
        this.nickname = nickname;
    }
}