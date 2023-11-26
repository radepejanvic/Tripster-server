package com.tripster.project.dto;

import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCruDTO {

    private Long id;
    private String email;
    private String password;
    private UserType userType;
    private UserStatus status;

    private String name;
    private String surname;
    private String phone;

    private String country;
    private String city;
    private String zipCode;
    private String street;
    private String number;

}
