package com.tripster.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    //Reservation info
    private Long id;
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private int guestsNo;
    private double price;
    //Guest info
    private Long guestId;
    //Basic accommodation info
    private Long accommodationId;

}
