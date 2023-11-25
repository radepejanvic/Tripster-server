package com.tripster.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User user;
}
