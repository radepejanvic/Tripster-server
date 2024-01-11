package com.tripster.project.mapper;

import com.tripster.project.dto.NotificationDTO;
import com.tripster.project.model.Notification;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class NotificationDTOMapper {

    public static Notification fromDTOtoNotification(NotificationDTO dto){
        Notification notification = new Notification();
        notification.setStatus(dto.getStatus());
        notification.setText(dto.getText());
        return notification;
    }
    public static NotificationDTO fromNotificationToDTO (Notification notification){
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setTitle(notification.getTitle());
        dto.setStatus(notification.getStatus());
        dto.setText(notification.getText());
        dto.setUserId(notification.getUser().getId());
        dto.setTimeStamp(notification.getTimeStamp().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy")));
        return dto;
    }
}
