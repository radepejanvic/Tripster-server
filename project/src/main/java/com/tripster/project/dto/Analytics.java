package com.tripster.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Analytics {

    private Long accommodationId;
    private String name;
    public long[] reservationsPerMonth;
    public double[] revenuePerMonth;
    private long totalReservations;
    private double totalRevenue;

    public Analytics() {
        reservationsPerMonth = new long[12];
        Arrays.fill(reservationsPerMonth, 0);

        revenuePerMonth = new double[12];
        Arrays.fill(revenuePerMonth, 0);
    }

}
