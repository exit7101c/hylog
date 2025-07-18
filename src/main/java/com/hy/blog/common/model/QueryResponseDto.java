package com.hy.blog.common.model;

import java.util.List;
import java.util.Map;

public class QueryResponseDto {

    private String sql; // 실행된 SQL 문
    private List<Map<String, Object>> result; // 결과 데이터

    public QueryResponseDto() {}

    public QueryResponseDto(String sql, List<Map<String, Object>> result) {
        this.sql = sql;
        this.result = result;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
