package com.hy.blog.feature.user;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.query.QueryExecutor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginHistoryLogger {

    private final QueryExecutor queryExecutor;

    public void log(QueryRequestDto request, List<Map<String, Object>> result) {
        if (!"USER_LOGIN".equalsIgnoreCase(request.getId())) return;

        Map<String, Object> params = request.getParam();
        String loginId = (String) params.get("USER_ID");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("LOGIN_ID", loginId);
        paramMap.put("IP_ADDRESS", extractIp());
        paramMap.put("USER_AGENT", extractUserAgent());
        paramMap.put("CREATED_BY", "SYSTEM");

        if (result == null || result.isEmpty()) {
            paramMap.put("USER_ID", null);
            paramMap.put("LOGIN_RESULT", "FAIL");
            paramMap.put("FAIL_REASON", "비밀번호 오류 또는 계정 없음");
        } else {
            paramMap.put("USER_ID", loginId);
            paramMap.put("LOGIN_RESULT", "SUCCESS");
        }

        QueryRequestDto dto = QueryRequestDto.builder()
                .id("USER_LOGIN_HISTORY_INSERT")
                .version("0001")
                .param(paramMap)
                .build();

        queryExecutor.executeUpdate(dto);
    }
    private String extractIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return req.getRemoteAddr();
    }

    private String extractUserAgent() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return req.getHeader("User-Agent");
    }

}
