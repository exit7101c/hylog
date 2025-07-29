package com.hy.blog.query;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.exec.QueryRouter;
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

