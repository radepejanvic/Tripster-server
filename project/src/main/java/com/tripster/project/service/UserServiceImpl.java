package com.tripster.project.service;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.repository.UserRepository;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public int updateStatus(Long id, UserStatus status) {
        return userRepository.updateStatus(id,status);
    }

}