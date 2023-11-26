package com.tripster.project.service.interfaces;

import com.tripster.project.model.Notification;
import com.tripster.project.model.User;

import java.util.List;

public interface INotificationService {

    public List<Notification> findByUser_Id(Long id);

    public Notification findById(Long id);

    public Notification save(Notification notification);
}
