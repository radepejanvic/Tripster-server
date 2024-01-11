package com.tripster.project.repository;

import com.tripster.project.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("select n " +
            "from Notification n " +
            "where n.user.id = :userId " +
            "and n.status = 'NEW' " +
            "order by n.timeStamp desc")
    List<Notification> findUnread(Long userId);
}
