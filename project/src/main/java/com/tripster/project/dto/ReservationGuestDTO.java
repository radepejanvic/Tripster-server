package com.tripster.project.dto;

import com.tripster.project.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationGuestDTO {

    private Long id;
    private String name;
    private byte[] photo;
    private String address;
    private int duration;
    private String timeStamp;
    private ReservationStatus status;
    private int numOfGuest;

    private double price;

}
