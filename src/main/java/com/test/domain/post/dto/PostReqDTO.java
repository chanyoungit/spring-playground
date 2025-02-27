package com.test.domain.post.dto;

public record PostReqDTO(
        String nickname,
        String title,
        String description
) {}