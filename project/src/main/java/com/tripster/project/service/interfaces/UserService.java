package com.tripster.project.service.interfaces;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;

import java.util.Optional;

public interface UserService {
    User findOne(Long id);

    Optional<User> findByUsername(String username);

    int updateStatus(Long id, UserStatus status);
}
