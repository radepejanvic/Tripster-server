package com.tripster.project.service.interfaces;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    User findOne(Long id);

    List<User> findAll();

    Page<User> findAll(Pageable page);

    User save(User User);

    void remove(Long id);

    User findByEmailAndPassword(String email,String password);

    int updateStatus(Long id, UserStatus status);
}
