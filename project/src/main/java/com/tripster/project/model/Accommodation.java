package com.tripster.project.model;

import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.Ammenity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Accommodation {
    // TODO: Add @Id tag when dependency is added
    private Long id;
    private String name;
    private String description;
    // TODO: Link with Host using OR mapper
    private Host owner;
    private List<Ammenity> amenities;
    private File photo;
    private int minCap;
    private int maxCap;
    private int cancelDuration;
    private AccommodationType accommodationType;
    private boolean automaticReservation;
    private AccommodationStatus status;
    // TODO: Link with Address using OR mapper
    private Address address;
}
