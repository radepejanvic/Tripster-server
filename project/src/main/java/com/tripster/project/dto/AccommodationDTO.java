package com.tripster.project.dto;

import com.tripster.project.model.Day;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AccommodationDTO {

    private Long id;

    private String name;

    private Long ownerId;

    private String country;

    private String city;

    private String zipCode;

    private String street;

    private String number;

    private String shortDescription;

    private String description;

    private Set<Long> amenities;

    private int minCap;

    private int maxCap;

    private int cancelDuration;

    private AccommodationType type;

    private boolean automaticReservation;

    private AccommodationStatus status;

    private Set<Day> calendar;

    private Double rating;

    private Long numOfReviews;

    private boolean pricePerNight;

    private Long ownerUserId;
}
