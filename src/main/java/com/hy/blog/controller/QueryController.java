package com.hy.blog.controller;

import com.hy.blog.common.model.QueryRequestDto;
import com.hy.blog.feature.service.QueryService;
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
