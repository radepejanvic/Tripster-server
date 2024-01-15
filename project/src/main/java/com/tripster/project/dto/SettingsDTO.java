package com.tripster.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettingsDTO {

    private Long id;

    private Long userId;

    private boolean reservationNotification;

    private boolean reviewNotification;

    private boolean accommodationReviewNotification;

}
