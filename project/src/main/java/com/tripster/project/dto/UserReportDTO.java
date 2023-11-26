package com.tripster.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportDTO {

    //Report info
    private Long Id;
    private String reason;

    //Reporter info
    private Long reporterId;
    private String reporterEmail;

    //Reportee info
    private Long reporteeId;
    private String reporteeEmail;

}
