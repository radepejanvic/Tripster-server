package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Day {
    @Id
    private Long id;
    private LocalDate date;
    private double price;
    private boolean isAvailable;
}
