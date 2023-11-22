package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String phone;
    // TODO: Add OneToOne relationship with User
    // TODO: Add OneToOne relationship with Address
}
