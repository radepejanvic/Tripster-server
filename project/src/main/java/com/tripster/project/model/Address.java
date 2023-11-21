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
public class Address {
    @Id
    private Long id;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private String number;
}
