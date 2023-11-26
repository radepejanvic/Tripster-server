package com.tripster.project.dto;

import com.tripster.project.model.Address;
import com.tripster.project.model.Amenity;
import com.tripster.project.model.Host;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.AmenityType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AccommodationDTO {

    private Long id;

    private String name;

    private Host owner;

    private Address address;

    private String description;

    private Set<Amenity> amenities;

    private int minCap;

    private int maxCap;

    private int cancelDuration;

    private AccommodationType type;

    private boolean automaticReservation;

    private AccommodationStatus status;
}
