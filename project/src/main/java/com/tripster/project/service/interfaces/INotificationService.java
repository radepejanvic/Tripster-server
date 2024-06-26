package com.tripster.project.service.interfaces;

import com.tripster.project.model.Notification;
import com.tripster.project.model.enums.NotificationStatus;

import java.util.List;

public interface INotificationService {

    List<Notification> findByUserId(Long id);

    Notification findOne(Long id);

    List<Notification> findByStatus(Long userId, NotificationStatus status);

    Notification save(Notification notification);
}
