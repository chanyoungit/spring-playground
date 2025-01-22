package com.test.domain.response.controller;


import com.test.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @GetMapping("/api/success")
    public ResponseEntity<ApiResponse<String>> success() {

        return ResponseEntity.ok(ApiResponse.success("성공!"));
    }

    @GetMapping("/api/fail")
    public ResponseEntity<ApiResponse<String>> fail() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("실패!"));
    }

    @GetMapping("/api/error")
    public ResponseEntity<ApiResponse<String>> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("서버에러 발생!"));
    }
}
