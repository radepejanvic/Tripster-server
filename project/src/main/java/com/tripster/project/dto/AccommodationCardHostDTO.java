package com.tripster.project.dto;

import com.tripster.project.model.Amenity;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Set;

@Getter
@Setter
public class AccommodationCardHostDTO {
    private Long id;

    private String name;

    private byte[] photo;

    private String address;
    private String shortDescription;
}
