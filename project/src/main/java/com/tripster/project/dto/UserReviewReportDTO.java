package com.tripster.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReviewReportDTO extends ReviewReportDTO{
    //Reviewed user info
    private Long reviewedUserId;
    private String reviewedUserEmail;
}
