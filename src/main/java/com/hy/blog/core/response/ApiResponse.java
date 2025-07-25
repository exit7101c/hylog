package com.hy.blog.core.response;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    public static Map<String, Object> success(String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", true);
        res.put("message", message);
        return res;
    }

    public static Map<String, Object> success(String message, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", true);
        res.put("message", message);
        res.put("data", data);
        return res;
    }

    public static Map<String, Object> error(String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", false);
        res.put("message", message);
        return res;
    }
}
