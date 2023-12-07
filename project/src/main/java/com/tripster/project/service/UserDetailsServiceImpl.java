package com.tripster.project.service;

import com.tripster.project.model.User;
import com.tripster.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ret = userRepository.findByEmail(username);
        if(!ret.isEmpty()){
            return org.springframework.security.core.userdetails.User.withUsername(username)
                    .password(ret.get().getPassword())
                    .roles(ret.get().getUserType().toString())
                    .disabled(ret.get().isDisabled())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with this username: "+username);
    }

}
