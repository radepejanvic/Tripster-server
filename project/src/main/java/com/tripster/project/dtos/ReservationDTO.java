package com.tripster.project.dtos;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    @Id
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false)
    private LocalDate start;
    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", nullable = false)
    private LocalDate end;
    private int duration;
    private int guestsNo;
    private double price;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private Guest guest;
    private Accommodation accommodation;

}
