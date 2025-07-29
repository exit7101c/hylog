package com.hy.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 모든 exec 및 API 요청에 대한 통일된 응답 형태
 */
@Data
@AllArgsConstructor(staticName = "of")
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    private String errorCode;

    // 성공 (데이터 없음)
    public static ApiResponse success(String message) {
        return of(true, message, null, null);
    }

    public static ApiResponse success(String message, Object data) {
        return of(true, message, data, null);
    }

    public static ApiResponse fail(String message) {
        return of(false, message, null, null);
    }

    public static ApiResponse fail(String message, String errorCode) {
        return of(false, message, null, errorCode);
    }

    public static ApiResponse fail(ApiErrorCode errorCode) {
        return of(false, null, null, errorCode.getCode());
    }
}
