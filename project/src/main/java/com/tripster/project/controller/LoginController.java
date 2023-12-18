package com.tripster.project.controller;

import com.tripster.project.dto.TokenDTO;
import com.tripster.project.dto.UserDTO;
import com.tripster.project.mapper.UserDTOMapper;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.security.jwt.JwtTokenUtil;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.UserService;
import org.aspectj.weaver.patterns.ITokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api")
public class LoginController {

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Qualifier("hostServiceImpl")
    @Autowired
    private IPersonService  hostService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> logIn(@RequestBody UserDTO userDTO) {

        User user = UserDTOMapper.fromUserDTOtoUser(userDTO);
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword());

        String token = "";
        HttpStatus status = HttpStatus.OK;
        TokenDTO dto = new TokenDTO();
        try {

            Authentication auth = authenticationManager.authenticate(authReq);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
            User userDet = userService.findByUsername(userDTO.getEmail()).get();
            Person person;
            if (userDet.getUserType().equals(UserType.GUEST)){
                person = guestService.findByUser(userDet);
                dto.setPersonID(person.getId().toString());
                dto.setUserID(person.getUser().getId().toString());

            }else if(userDet.getUserType().equals(UserType.HOST)){
                person = hostService.findByUser(userDet);
                dto.setPersonID(person.getId().toString());
                dto.setUserID(person.getUser().getId().toString());
            }
            else{
                dto.setUserID("0");
                dto.setPersonID("0");
            }
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {

            Optional<User> ret = userService.findByUsername(userDTO.getEmail());

            if (ret.isEmpty()) {
                status = HttpStatus.NOT_FOUND;
            }
            else
            {
                switch (ret.get().getStatus()){
                    case NEW -> {
                        status = HttpStatus.UNAUTHORIZED;
                    }
                    case SUSPENDED -> {
                        status = HttpStatus.FORBIDDEN;
                    }
                    case DELETED -> {
                        status = HttpStatus.NOT_FOUND;
                    }
                }
            }
        }
        dto.setToken(token);
        return new ResponseEntity<>(dto, status);
    }

    @GetMapping(
            value = "/logOut",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity logOut() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();

            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new RuntimeException("User is not authenticated!");
        }

    }
}
