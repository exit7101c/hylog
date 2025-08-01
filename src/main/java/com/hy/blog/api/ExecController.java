package com.hy.blog.api;

import com.hy.blog.common.ApiResponse;
import com.hy.blog.common.ExecRequestDto;
import com.hy.blog.exec.ExecutableHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exec")
@RequiredArgsConstructor
public class ExecController {

    private final ApplicationContext context;

    @PostMapping
    public ResponseEntity<?> exec(@RequestBody ExecRequestDto req) {
        Object bean = context.getBean(req.getName());

        if (!(bean instanceof ExecutableHandler handler)) {
            return ResponseEntity.badRequest().body(ApiResponse.fail("등록되지 않은 handler: " + req.getName()));
        }

        Object result = handler.handle(req.getParam());
        return ResponseEntity.ok(result);
    }
}
