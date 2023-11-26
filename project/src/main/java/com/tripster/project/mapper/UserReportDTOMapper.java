package com.tripster.project.mapper;

import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.model.UserReport;

public class UserReportDTOMapper {
    public static UserReport fromDTOToUserReport(UserReportDTO dto) {
        if (dto == null) {
            return null;
        }
        UserReport userReport = new UserReport();
        userReport.setId(dto.getId());
        userReport.setReason(dto.getReason());
        return userReport;
    }
    public static UserReportDTO fromUserReportToDTO(UserReport userReport) {
        if (userReport == null) {
            return null;
        }
        UserReportDTO dto = new UserReportDTO();
        dto.setId(userReport.getId());
        dto.setReason(userReport.getReason());
        dto.setReporterId(dto.getReporterId());
        dto.setReporterEmail(dto.getReporterEmail());
        dto.setReporteeId(dto.getReporteeId());
        dto.setReporteeEmail(dto.getReporteeEmail());
        return dto;
    }
}
