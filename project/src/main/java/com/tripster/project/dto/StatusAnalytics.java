package com.tripster.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusAnalytics {

    private long pendingCount;
    private double pendingRevenue;

    private long acceptedCount;
    private double acceptedRevenue;

    private long cancelledCount;
    private double cancelledRevenue;

    private long rejectedCount;
    private double rejectedRevenue;

}
