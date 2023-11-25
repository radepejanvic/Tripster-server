package com.tripster.project.dto;

import com.tripster.project.model.Amenity;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;

import java.io.File;
import java.time.LocalDate;
import java.util.Set;

public class AccommodationCardHostDTO {
    private Long id;

    private String name;

    // TODO: Change to photo data (byte array or something similar)
    private File photo;

    private float distanceFromCenter;

    private boolean isFreeCancellation;

    private AccommodationStatus status;

    // TODO: Add to Model class
    private String shortDescription;

    private AccommodationType type;

    // TODO: Take just first 3 amenities
    private Set<Amenity> amenities;

    // TODO: Maybe add rating here too
//    private float rating;
//    private int numOfReviews;
}
