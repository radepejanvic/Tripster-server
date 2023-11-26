package com.tripster.project.mapper;

import com.tripster.project.dto.NotificationDTO;
import com.tripster.project.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDTOMapper {

    public static Notification fromDTOtoNotification(NotificationDTO dto){
        Notification notification = new Notification();
        notification.setStatus(dto.getStatus());
        notification.setText(dto.getText());
        return notification;
    }
    public static NotificationDTO fromNotificationToDTO (Notification notification){
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setStatus(notification.getStatus());
        notificationDTO.setText(notification.getText());
        notificationDTO.setUserId(notification.getUser().getId());
        return notificationDTO;
    }
}
