package com.tripster.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserReportDTO {

    private Long Id;

    private String reason;

    private Long reporterId;

    private String reporterEmail;

    private Long reporteeId;

    private String reporteeEmail;

}
