package com.tripster.project.model;

import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private UserType userType;
    private UserStatus status;
}
