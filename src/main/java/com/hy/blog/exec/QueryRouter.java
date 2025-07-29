package com.hy.blog.exec;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.feature.user.LoginService;
import com.hy.blog.query.QueryExecutor;
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
