package com.tripster.project.model;

import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.AmenityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Host owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Address address;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<AmenityType> amenities;

    private int minCap;

    private int maxCap;

    private int cancelDuration;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;

    private boolean automaticReservation;

    @Enumerated(EnumType.STRING)
    private AccommodationStatus status;
}
