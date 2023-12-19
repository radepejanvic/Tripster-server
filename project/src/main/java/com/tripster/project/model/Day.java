package com.tripster.project.model;

import com.tripster.project.model.enums.DayStatus;
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
    @Enumerated(EnumType.STRING)
    private DayStatus availability;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "accommodation_id")
//    private Accommodation accommodation;
}
