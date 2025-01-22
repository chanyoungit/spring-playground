package com.test.common.response;

public class ApiResponse<T> {
    private String status;
    private T data;
    private String error;

//    public ApiResponse() {
//    }

    // 성공 응답 메서드
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "success";
        response.data = data;
        response.error = null;
        return response;
    }

    // 실패 응답 메서드
    public static <T> ApiResponse<T> error(String errorMessage) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "error";
        response.data = null;
        response.error = errorMessage;
        return response;
    }

    public String getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getError() {
        return this.error;
    }
}
