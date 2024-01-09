package com.tripster.project.mapper;

import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.model.UserReport;

public class UserReportDTOMapper {

    public static UserReport fromDTOToUserReport(UserReportDTO dto) {
        UserReport userReport = new UserReport();

        userReport.setId(dto.getId());
        userReport.setReason(dto.getReason());

        return userReport;
    }

    public static UserReportDTO fromUserReportToDTO(UserReport report) {
        UserReportDTO dto = new UserReportDTO();

        dto.setId(report.getId());
        dto.setReason(report.getReason());
        dto.setReporterId(report.getReporter().getId());
        dto.setReporteeId(report.getReportee().getId());
        dto.setReporterEmail(report.getReporter().getEmail());
        dto.setReporteeEmail(report.getReportee().getEmail());

        return dto;
    }
}
