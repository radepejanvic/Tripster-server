package com.tripster.project.controller;

import com.tripster.project.dto.NotificationDTO;
import com.tripster.project.dto.StatusDTO;
import com.tripster.project.mapper.NotificationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Notification;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.NotificationStatus;
import com.tripster.project.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/notifications")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;



    @GetMapping(value = "/{id}")
    public ResponseEntity<List<NotificationDTO>> getAll(@PathVariable Long id){

        List<Notification> notifications = notificationService.findByUserId(id);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(NotificationDTOMapper::fromNotificationToDTO).toList();

        return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/unread/{id}")
    public ResponseEntity<List<NotificationDTO>> getUnread(@PathVariable Long id){

        List<NotificationDTO> notifications = notificationService.findByStatus(id, NotificationStatus.NEW).stream()
                .map(NotificationDTOMapper::fromNotificationToDTO).toList();

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<List<NotificationDTO>> getRead(@PathVariable Long id){

        List<NotificationDTO> notifications = notificationService.findByStatus(id, NotificationStatus.READ).stream()
                .map(NotificationDTOMapper::fromNotificationToDTO).toList();

        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST') || hasRole('HOST')")
    @PatchMapping(consumes = "application/json")
    public ResponseEntity<String> markAsRead(@RequestBody StatusDTO dto) {

        Notification notification = notificationService.findOne(dto.getId());

        if(notification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        notification.setStatus(NotificationStatus.READ);
        notificationService.save(notification);

        return new ResponseEntity<>(notification.getStatus().toString(), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Notification notification = notificationService.findOne(id);
        if (notification == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        notification.setStatus(NotificationStatus.READ);
        notificationService.save(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
