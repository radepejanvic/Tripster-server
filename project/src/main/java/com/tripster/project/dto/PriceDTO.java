package com.tripster.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PriceDTO {

    private LocalDate start;
    private LocalDate end;
    private double price;
}
