package com.test.domain.response.controller;


import com.test.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @GetMapping("/api/success")
    public ApiResponse<String> success() {
        return ApiResponse.success("성공!");
    }

    @GetMapping("/api/fail")
    public ApiResponse<String> fail() {
        throw new IllegalArgumentException("잘못된 요청입니다.");
    }

    @GetMapping("/api/error")
    public ApiResponse<String> serverError() {
        throw new RuntimeException("서버 내부 오류 발생!");
    }
}
