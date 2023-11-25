package com.tripster.project.dto;

import com.tripster.project.model.Address;
import com.tripster.project.model.Host;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.AmenityType;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class AccommodationDTO {

    private Long id;

    private String name;

    private Host owner;

    private Address address;

    private String description;

    private List<AmenityType> amenities;

    private File photo;

    private int minCap;

    private int maxCap;

    private int cancelDuration;

    private AccommodationType accommodationType;

    private boolean automaticReservation;

    private AccommodationStatus accommodationStatus;
}
