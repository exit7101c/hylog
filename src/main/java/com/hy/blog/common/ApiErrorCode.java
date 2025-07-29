package com.hy.blog.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorCode {
    DUPLICATE_USER("USR001", "이미 존재하는 사용자입니다."),
    INVALID_INPUT("USR002", "입력값이 유효하지 않습니다.");

    private final String code;
    private final String logMessage;
}