package com.tripster.project.model;

import com.tripster.project.model.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int duration;
    private int guests;
    private double price;
    private ReservationStatus status;
    // TODO: Link with Guest using OR mapper
    //private Guest guest;
    // TODO: Link with Accommodation using OR mapper
    //private Accommodation accommodation;
}
