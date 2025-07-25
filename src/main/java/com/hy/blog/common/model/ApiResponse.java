package com.hy.blog.common.model;

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

    // 성공 (데이터 없음)
    public static ApiResponse success(String message) {
        return of(true, message, null);
    }

    // 성공 (데이터 있음)
    public static ApiResponse success(String message, Object data) {
        return of(true, message, data);
    }

    // 실패
    public static ApiResponse fail(String message) {
        return of(false, message, null);
    }
}
