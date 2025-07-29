package com.hy.blog.security;

import com.hy.blog.common.QueryRequestDto;
import com.hy.blog.query.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final QueryService queryService; // ← 너가 만든 SQL 실행기

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String loginId = (String) event.getAuthentication().getPrincipal();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("USER_ID", null);
        paramMap.put("LOGIN_ID", loginId);
        paramMap.put("LOGIN_RESULT", "FAIL");
        paramMap.put("FAIL_REASON", "비밀번호 오류 또는 계정 없음");
        paramMap.put("IP_ADDRESS", ip);
        paramMap.put("USER_AGENT", userAgent);
        paramMap.put("CREATED_BY", "SYSTEM");

        QueryRequestDto dto = QueryRequestDto.builder()
                .id("USER_LOGIN_HISTORY_INSERT")
                .version("0001")
                .param(paramMap)
                .build();

//        queryService.executeUpdate(dto); // ← 너가 만든 쿼리 실행기 메서드
    }
}