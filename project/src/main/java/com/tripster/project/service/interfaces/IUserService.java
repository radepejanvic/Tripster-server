package com.tripster.project.service.interfaces;

import com.tripster.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    public User findOne(Long id);

    public List<User> findAll();

    public Page<User> findAll(Pageable page);

    public User save(User User);

    public void remove(Long id);

    public User findByEmailAndPassword(String email,String password);
}
