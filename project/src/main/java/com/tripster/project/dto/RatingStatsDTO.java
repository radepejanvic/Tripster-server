package com.tripster.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RatingStatsDTO {
    private double rating;
    private long reviews;
    private long excellent;
    private long good;
    private long average;
    private long poor;
    private long bad;
}
