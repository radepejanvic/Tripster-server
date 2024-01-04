package com.tripster.project.dto;

import com.tripster.project.model.Report;
import com.tripster.project.model.ReviewReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccommodationReviewReportDTO extends ReviewReportDTO {

    private Long id;

    private String reason;

    private Long reporterId;

    private Long reviewId;

}
