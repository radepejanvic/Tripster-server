package com.tripster.project.dto;

import com.tripster.project.model.Report;
import com.tripster.project.model.ReviewReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationReviewReportDTO extends ReviewReportDTO {

    //Report info
    private Long id;
    private String reason;

    //Reporter info
    private Long reporterId;
    private String reporterEmail;

    //Review info
    private Long reviewId;

    //Reviewed accommodation info
    private Long reviewedAccId;
    private String accName;
    private Long accAdressId;

    //Accommodation adress info
    private String accCountry;
    private String accCity;
    private String zipCode;
    private String accStreet;
    private String accNumber;

    //Accommodation owner info
    private Long accOwnerId;
    private String accOwnerEmail;

}
