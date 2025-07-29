package com.hy.blog.exec;

import java.util.Map;

public interface ExecutableHandler {
    Object handle(Map<String, Object> param);
}
