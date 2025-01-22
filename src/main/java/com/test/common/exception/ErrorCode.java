package com.test.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID_INPUT_VALUE", "잘못된 입력 값입니다."),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "INVALID_ARGUMENT", "잘못된 요청입니다."),

    // 404 Not Found
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", "리소스를 찾을 수 없습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
