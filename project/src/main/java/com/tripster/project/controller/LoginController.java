package com.tripster.project.controller;

import com.tripster.project.dto.UserDTO;
import com.tripster.project.mapper.UserDTOMapper;
import com.tripster.project.model.User;
import com.tripster.project.security.jwt.JwtTokenUtil;
import com.tripster.project.service.interfaces.UserService;
import org.aspectj.weaver.patterns.ITokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {

        User user = UserDTOMapper.fromUserDTOtoUser(userDTO);
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword());

        String token = "";
        HttpStatus status = HttpStatus.OK;
        try {

            Authentication auth = authenticationManager.authenticate(authReq);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {

            Optional<User> ret = userService.findByUsername(userDTO.getEmail());

            if (ret.isEmpty()) {
                token = "User doesn't exist.";
                status = HttpStatus.NOT_FOUND;
            }
            else
            {
                switch (ret.get().getStatus()){
                    case NEW -> {
                        token = "Email isn't validated.";
                        status = HttpStatus.UNAUTHORIZED;
                    }
                    case SUSPENDED -> {
                        token = "User is suspended.";
                        status = HttpStatus.UNAUTHORIZED;
                    }
                    case DELETED -> {
                        token = "User doesn't exist.";
                        status = HttpStatus.NOT_FOUND;
                    }
                }
            }
        }
        return new ResponseEntity<>(token, status);
    }

}
