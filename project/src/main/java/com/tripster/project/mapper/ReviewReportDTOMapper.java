package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.dto.UserReviewReportDTO;
import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.model.UserReviewReport;

public class ReviewReportDTOMapper {

    public static UserReviewReport fromDTOToUserReviewReport(UserReviewReportDTO dto) {

        UserReviewReport report = new UserReviewReport();

        report.setId(dto.getId());
        report.setReason(dto.getReason());

        return report;
    }

    public static UserReviewReportDTO fromUserReviewReportToDTO(UserReviewReport report) {

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

        AccommodationReviewReport report = new AccommodationReviewReport();

        report.setId(dto.getId());
        report.setReason(dto.getReason());

        return report;
    }
    public static AccommodationReviewReportDTO fromAccommodationReviewReportToDTO(AccommodationReviewReport report, byte[] photo) {

        AccommodationReviewReportDTO dto = new AccommodationReviewReportDTO();

        dto.setName(report.getReview().getAccommodation().getName());
        dto.setPhoto(photo);
        dto.setComment(report.getReview().getComment());
        dto.setId(report.getId());
        dto.setReason(report.getReason());
        dto.setReporterEmail(report.getReporter().getEmail());

        return dto;
    }
}
