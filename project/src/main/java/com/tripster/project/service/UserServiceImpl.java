package com.tripster.project.service;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.repository.UserRepository;
import com.tripster.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id) {return userRepository.findById(id).orElse(null);}

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    public User save(User User) {
        return userRepository.save(User);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email,password);
        return user;
    }

    @Override
    public int updateStatus(Long id, UserStatus status) {
        return userRepository.updateStatus(id,status);
    }

}