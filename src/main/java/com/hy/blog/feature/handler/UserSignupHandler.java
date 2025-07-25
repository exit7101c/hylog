package com.hy.blog.feature.handler;

import com.hy.blog.core.exec.ExecutableHandler;
import com.hy.blog.core.response.ApiResponse;
import com.hy.blog.feature.repository.UserRepository;
import com.hy.blog.feature.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

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

        //  간단한 중복 체크
        if (userRepository.existsByUserId(userId)) {
            return Map.of("message", "이미 존재하는 사용자입니다.");
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
