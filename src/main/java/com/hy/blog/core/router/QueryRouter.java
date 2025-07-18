package com.hy.blog.core.router;

import com.hy.blog.common.model.QueryRequestDto;
import com.hy.blog.core.executor.QueryExecutor;
import com.hy.blog.feature.service.LoginService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class QueryRouter {

    private final Map<String, Function<QueryRequestDto, Object>> routerMap = new HashMap<>();

    public QueryRouter(LoginService loginService,
                       QueryExecutor queryExecutor
//                       PostService postService,
                       ) {

        routerMap.put("USER_LOGIN", loginService::doLogin);
        routerMap.put("DEFAULT", queryExecutor::executeQuery);
//        routerMap.put("POST_CREATE", postService::createPost);
    }

    public Object route(QueryRequestDto request) {
        return routerMap.getOrDefault(request.getId(), routerMap.get("DEFAULT")).apply(request);
    }
}
