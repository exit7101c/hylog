package com.hy.blog.api;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QueryController {

    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/query")
    public Object runDynamicQuery(@RequestBody QueryRequestDto request) {
        return queryService.runQuery(request);
    }

}
