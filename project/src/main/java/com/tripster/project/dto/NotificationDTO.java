package com.tripster.project.dto;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {

    private Long id;

    private String title;

    private String text;

    private NotificationStatus status;

    private String timeStamp;

    private Long userId;
}
