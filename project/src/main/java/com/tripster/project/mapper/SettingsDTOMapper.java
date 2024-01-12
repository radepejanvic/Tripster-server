package com.tripster.project.mapper;

import com.tripster.project.dto.SettingsDTO;
import com.tripster.project.model.Settings;

public class SettingsDTOMapper {

    public static void setFromDTO(Settings settings, SettingsDTO dto) {

        settings.setReservationNotification(dto.isReservationNotification());
        settings.setReviewNotification(dto.isReviewNotification());
        settings.setAccommodationReviewNotification(dto.isAccommodationReviewNotification());

    }

    public static SettingsDTO fromSettingsToDTO(Settings settings) {

        SettingsDTO dto = new SettingsDTO();

        dto.setId(settings.getId());
        dto.setUserId(settings.getUser().getId());
        dto.setReservationNotification(settings.isReservationNotification());
        dto.setReviewNotification(settings.isReviewNotification());
        dto.setAccommodationReviewNotification(settings.isAccommodationReviewNotification());

        return dto;
    }
    
}
