package com.tripster.project.model;

import com.tripster.project.model.enums.NotificationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    private Long id;
    private String text;
    private NotificationStatus status;
    // TODO: Add ManyToOne relationship with User
}
