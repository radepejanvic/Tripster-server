package com.tripster.project.service.interfaces;

import com.tripster.project.model.Notification;

import java.util.List;

public interface INotificationService {

    List<Notification> findByUserId(Long id);

    Notification findOne(Long id);

    List<Notification> findUnread(Long userId);

    Notification save(Notification notification);
}
