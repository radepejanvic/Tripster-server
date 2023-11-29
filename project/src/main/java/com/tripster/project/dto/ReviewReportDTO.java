package com.tripster.project.dto;

import com.tripster.project.model.enums.ReviewStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ReviewReportDTO {
    //Report info
    private Long Id;
    private String reason;

    //Reporter info
    private Long reporterId;
    private String reporterEmail;

    //Review info
    private Long reviewId;
    private int rate;
    private String comment;
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    //Reviewer info
    private Long reviewerId;
    private String reviewerEmail;


}
