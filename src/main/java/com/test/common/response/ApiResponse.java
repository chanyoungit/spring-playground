package com.test.common.response;

import com.test.common.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;

    // 성공 응답 메서드
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.error = null;
        return response;
    }

    // 실패 응답 메서드
    public static <T> ApiResponse<T> error(ErrorResponse errorResponse) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.data = null;
        response.error = errorResponse;
        return response;
    }
}
