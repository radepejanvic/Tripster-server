package com.tripster.project.model;

import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    public boolean isDisabled(){
        return (!status.equals(UserStatus.ACTIVE));
    }
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<Notification> notifications;
}
