package com.tripster.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private boolean reservationNotification;

    @Column(nullable = false)
    private boolean reviewNotification;

    @Column(nullable = false)
    private boolean accommodationReviewNotification;

    public Settings(User user) {
        this.user = user;
        reservationNotification = true;
        reviewNotification = true;
        accommodationReviewNotification = true;
    }

}
