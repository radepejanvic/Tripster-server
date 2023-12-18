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

    private byte[] photo;

    private String address;

    private AccommodationStatus status;

    private String timeStamp;

    private String shortDescription;
}
