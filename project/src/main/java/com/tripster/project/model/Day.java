package com.tripster.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @NonNull
    @Column(nullable = false)
    private double price;

    @NonNull
    private boolean isAvailable;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "accommodation_id")
//    private Accommodation accommodation;
}
