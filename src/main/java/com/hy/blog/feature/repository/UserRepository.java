package com.hy.blog.feature.repository;

import com.hy.blog.feature.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
}
