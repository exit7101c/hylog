package com.hy.blog.query;

import com.hy.blog.common.QueryRequestDto;

public interface QueryExecutor {
    Object executeUpdate(QueryRequestDto dto);

    Object executeQuery(QueryRequestDto dto);
}
