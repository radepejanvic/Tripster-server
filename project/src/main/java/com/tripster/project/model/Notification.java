package com.tripster.project.model;

import com.tripster.project.model.enums.NotificationStatus;
import com.tripster.project.model.enums.NotificationType;
import com.tripster.project.model.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User user;

    private LocalDateTime timeStamp;

    public Notification(Reservation reservation) {
        title = getReservationTitle(reservation.getStatus());
        text = generateReservationText(reservation);
        type = NotificationType.RESERVATION;
        status = NotificationStatus.NEW;
        if (reservation.getStatus() == ReservationStatus.ACCEPTED || reservation.getStatus() == ReservationStatus.REJECTED) {
            this.user = reservation.getGuest().getUser();
        } else {
            this.user = reservation.getAccommodation().getOwner().getUser();
        }
        timeStamp = LocalDateTime.now();
    }

    public Notification(UserReview review) {
        title = "New review";
        text = generateUserReviewText(review);
        type = NotificationType.REVIEW;
        status = NotificationStatus.NEW;
        this.user = review.getReviewedUser();
        timeStamp = LocalDateTime.now();
    }

    public Notification(AccommodationReview review) {
        title = "New accommodation review";
        text = generateAccommodationReviewText(review);
        type = NotificationType.ACCOMMODATION_REVIEW;
        status = NotificationStatus.NEW;
        this.user = review.getAccommodation().getOwner().getUser();
        timeStamp = LocalDateTime.now();
    }

    private String getReservationTitle(ReservationStatus status) {
        return switch (status) {
            case REJECTED -> "Reservation rejected";
            case CANCELLED -> "Reservation cancelled";
            case PENDING -> "New reservation";
            case ACCEPTED -> "Reservation accepted";
        };
    }

    private String generateReservationText(Reservation reservation) {

        return "Accommodation: " +
                reservation.getAccommodation().getName() +
                "\nGuest: " +
                reservation.getGuest().getUser().getEmail() +
                "\nScheduled for: " +
                reservation.getStart() +
                " - " +
                reservation.getEnd();
    }

    private String generateUserReviewText(UserReview review) {
        return "Reviewer: " +
                review.getReviewer().getEmail() +
                "\nTitle: " +
                review.getTitle() +
                "\nRate: " +
                review.getRate() +
                "\nComment: " +
                review.getComment();
    }

    private String generateAccommodationReviewText(AccommodationReview review) {
        return "Accommodation: " +
                review.getAccommodation().getName() +
                "\nReviewer: " +
                review.getReviewer().getEmail() +
                "\nTitle: " +
                review.getTitle() +
                "\nRate: " +
                review.getRate() +
                "\nComment: " +
                review.getComment();
    }

}
