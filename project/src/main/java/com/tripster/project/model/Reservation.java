package com.tripster.project.model;

import com.tripster.project.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean passed = false;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;
}
