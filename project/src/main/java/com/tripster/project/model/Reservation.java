package com.tripster.project.model;

import com.tripster.project.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    // TODO: Add @Id tag when dependency is added
    private Long id;
    private LocalDate start;
    private LocalDate end;
    private int duration;
    private int guests;
    private double price;
    private ReservationStatus status;
    // TODO: Link with Guest using OR mapper
    private Guest guest;
    // TODO: Link with Accommodation using OR mapper
    private Accommodation accommodation;
}
