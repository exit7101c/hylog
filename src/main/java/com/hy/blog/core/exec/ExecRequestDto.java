package com.hy.blog.core.exec;

import lombok.Data;

import java.util.Map;

@Data
public class ExecRequestDto {
    private String name;
    private Map<String, Object> param;
}