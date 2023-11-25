package com.tripster.project.dto;

import com.tripster.project.model.Amenity;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class AccommodationCardAdminDTO {

    private Long id;

    private String name;

    // TODO: Change to photo data (byte array or something similar)
    private File photo;

    private float distanceFromCenter;

    // TODO: Concatenated owner name and surname
    private String owner;

    private boolean isFreeCancellation;

    private AccommodationStatus status;

    // TODO: Add to Model class
    private LocalDate timeStamp;

    // TODO: Add to Model class
    private String shortDescription;

    private AccommodationType type;

    // TODO: Take just first 3 amenities
    private Set<Amenity> amenities;

}
