package com.hy.blog.feature.service;

import com.hy.blog.common.model.QueryRequestDto;
import com.hy.blog.core.router.QueryRouter;
import org.springframework.stereotype.Service;

@Service
public class QueryService  {
    private final QueryRouter queryRouter;

    public QueryService(QueryRouter queryRouter) {
        this.queryRouter = queryRouter;
    }

    public Object runQuery(QueryRequestDto request) {
        return queryRouter.route(request);
    }

}

