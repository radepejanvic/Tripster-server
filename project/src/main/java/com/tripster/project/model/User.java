package com.tripster.project.model;

import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String email;
    private String password;
    private UserType userType;
    private UserStatus status;
}
