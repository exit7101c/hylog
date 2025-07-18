package com.hy.blog.core.executor;

import com.hy.blog.common.model.QueryRequestDto;

public interface QueryExecutor {
    Object executeUpdate(QueryRequestDto dto);

    Object executeQuery(QueryRequestDto dto);
}
