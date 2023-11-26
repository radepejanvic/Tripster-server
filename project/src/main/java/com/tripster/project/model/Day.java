package com.tripster.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double price;

    private boolean isAvailable;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "accommodation_id")
//    private Accommodation accommodation;
}
