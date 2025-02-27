package com.test.domain.post.dto;

public record PostResDTO(
    Long id,
    String nickname,
    String title,
    String description
) {}