package com.tripster.project.service;

import com.tripster.project.model.Notification;
import com.tripster.project.model.enums.NotificationStatus;
import com.tripster.project.repository.NotificationRepository;
import com.tripster.project.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public List<Notification> findByUserId(Long id) {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findOne(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Notification> findByStatus(Long userId, NotificationStatus status) {
        return notificationRepository.findByStatus(userId, status);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }
}
