package com.hy.blog.security;

import com.hy.blog.feature.user.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {

    @PrePersist
    public void prePersist(Object obj) {
        if (obj instanceof User user) {
            if (user.getCreatedAt() == null) {
                user.setCreatedAt(LocalDateTime.now());
            }
            if (user.getValidState() == null) {
                user.setValidState("Valid");
            }
        }
    }
    @PreUpdate
    public void preUpdate(Object obj) {
        if (obj instanceof User user) {
            user.setUpdatedAt(LocalDateTime.now());
        }
    }
}
