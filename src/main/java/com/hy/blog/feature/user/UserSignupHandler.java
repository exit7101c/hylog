package com.hy.blog.feature.user;

import com.hy.blog.common.ApiErrorCode;
import com.hy.blog.exec.ExecutableHandler;
import com.hy.blog.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Component("USER_SIGNUP_HANDLER")
@RequiredArgsConstructor
public class UserSignupHandler implements ExecutableHandler {

    private final UserRepository userRepository;

    @Override
    public Object handle(Map<String, Object> param) {
        String userId = (String) param.get("USER_ID");
        String password = (String) param.get("PASSWORD");
        String nickname = (String) param.get("NICKNAME");
        String email = (String) param.get("EMAIL");
        String userName = (String) param.get("USER_NAME");

        // 중복 체크
        if (userRepository.existsByUserId(userId)) {
            ApiErrorCode errorCode = ApiErrorCode.DUPLICATE_USER;
            // log the error message
            log.warn("[{}] {}", errorCode.getCode(), errorCode.getLogMessage());
            return ApiResponse.fail(errorCode);
        }

        User newUser = User.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .userName(userName)
                .createdBy("SYSTEM")
                .build();

        userRepository.save(newUser);

        return ApiResponse.success("회원가입이 완료되었습니다.");
    }
}
