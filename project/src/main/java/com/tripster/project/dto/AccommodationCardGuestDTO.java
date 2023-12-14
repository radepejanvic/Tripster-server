package com.tripster.project.dto;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Amenity;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AccommodationCardGuestDTO {
    private Long id;

    private String name;

    private byte[] photo;

    private String shortDescription;

    private String address;

    private double price;

    private double pricePerNight;

    private Long duration;

    private int numOfGuests;

    private float rating;

    private int numOfReviews;

}
