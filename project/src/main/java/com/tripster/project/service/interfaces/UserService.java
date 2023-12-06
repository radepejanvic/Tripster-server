package com.tripster.project.service.interfaces;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;

public interface UserService {
    User findOne(Long id);

    int updateStatus(Long id, UserStatus status);
}
