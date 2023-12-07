package com.tripster.project.dto;

import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String password;
}
