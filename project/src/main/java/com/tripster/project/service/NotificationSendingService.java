package com.tripster.project.service;

import com.tripster.project.mapper.NotificationDTOMapper;
import com.tripster.project.model.Notification;
import com.tripster.project.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationSendingService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    public void send(Notification notification) {
        notificationRepository.save(notification);
        simpMessagingTemplate.convertAndSend("/socket-publisher/" + notification.getUser().getId() , NotificationDTOMapper.fromNotificationToDTO(notification));
    }

}
