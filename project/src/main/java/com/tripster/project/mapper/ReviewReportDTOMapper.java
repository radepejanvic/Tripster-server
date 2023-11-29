package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.dto.UserReviewReportDTO;
import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.model.UserReviewReport;

public class ReviewReportDTOMapper {
    public static UserReviewReport fromDTOToUserReviewReport(UserReviewReportDTO dto) {
        if (dto == null) {
            return null;
        }
        UserReviewReport report = new UserReviewReport();
        report.setId(dto.getId());
        report.setReason(dto.getReason());
        return report;
    }
    public static UserReviewReportDTO fromUserReviewReportToDTO(UserReviewReport report) {
        if (report == null) {
            return null;
        }
        UserReviewReportDTO dto = new UserReviewReportDTO();
        dto.setId(report.getId());
        dto.setReason(report.getReason());

        dto.setReporterId(report.getReporter().getId());
        dto.setReporterEmail(report.getReporter().getEmail());

        dto.setReviewId(report.getReview().getId());
        dto.setRate(report.getReview().getRate());
        dto.setComment(report.getReview().getComment());
        dto.setReviewerId(report.getReview().getReviewer().getId());
        dto.setReviewerEmail(report.getReview().getReviewer().getEmail());

        return dto;
    }

    public static AccommodationReviewReport fromDTOToAccommodationReviewReport(AccommodationReviewReportDTO dto) {
        if (dto == null) {
            return null;
        }
        AccommodationReviewReport report = new AccommodationReviewReport();
        report.setId(dto.getId());
        report.setReason(dto.getReason());
        return report;
    }
    public static AccommodationReviewReportDTO fromAccommodationReviewReportToDTO(AccommodationReviewReport report) {
        if (report == null) {
            return null;
        }
        AccommodationReviewReportDTO dto = new AccommodationReviewReportDTO();
        dto.setId(report.getId());
        dto.setReason(report.getReason());
        dto.setReporterId(report.getReporter().getId());
        dto.setReporterEmail(report.getReporter().getEmail());

        dto.setReviewId(report.getReview().getId());

        dto.setReviewedAccId(report.getReview().getAccommodation().getId());
        dto.setAccName(report.getReview().getAccommodation().getName());

        dto.setAccAdressId(report.getReview().getAccommodation().getAddress().getId());
        dto.setAccCountry(report.getReview().getAccommodation().getAddress().getCountry());
        dto.setAccCity(report.getReview().getAccommodation().getAddress().getCity());
        dto.setZipCode(report.getReview().getAccommodation().getAddress().getZipCode());
        dto.setAccStreet(report.getReview().getAccommodation().getAddress().getZipCode());
        dto.setAccNumber(report.getReview().getAccommodation().getAddress().getNumber());

        dto.setAccOwnerId(report.getReview().getAccommodation().getOwner().getId());
        dto.setAccOwnerEmail(report.getReview().getAccommodation().getOwner().getUser().getEmail());

        return dto;
    }
}
