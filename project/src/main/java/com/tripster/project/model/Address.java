package com.tripster.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private Long id;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private String number;
}
