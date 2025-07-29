package com.hy.blog.feature.user;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.query.QueryExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final QueryExecutor queryExecutor;
    private final LoginHistoryLogger loginHistoryLogger;
    public Object doLogin(QueryRequestDto request) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) queryExecutor.executeQuery(request);

        // 로그인 결과에 따라 로그인 히스토리 기록
        loginHistoryLogger.log(request, result);

        return result;
    }
}