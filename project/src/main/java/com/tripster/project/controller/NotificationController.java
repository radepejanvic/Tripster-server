package com.tripster.project.controller;

import com.tripster.project.dto.NotificationDTO;
import com.tripster.project.mapper.NotificationDTOMapper;
import com.tripster.project.model.Notification;
import com.tripster.project.model.enums.NotificationStatus;
import com.tripster.project.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<NotificationDTO>> getAll(@PathVariable Long id){

        List<Notification> notifications = notificationService.findByUser_Id(id);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(NotificationDTOMapper::fromNotificationToDTO).toList();

        return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Notification notification = notificationService.findById(id);
        if (notification == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        notification.setStatus(NotificationStatus.READ);
        notificationService.save(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
