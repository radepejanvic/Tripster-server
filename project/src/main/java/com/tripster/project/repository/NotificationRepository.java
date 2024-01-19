package com.tripster.project.repository;

import com.tripster.project.model.Notification;
import com.tripster.project.model.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("select n " +
            "from Notification n " +
            "where n.user.id = :userId " +
            "and n.status = :status " +
            "order by n.timeStamp desc")
    List<Notification> findByStatus(Long userId, NotificationStatus status);
}
