package com.tripster.project.service;

import com.tripster.project.model.*;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class NotificationSendingServiceTest {

    private Reservation reservation;
    private User user;

    @Autowired
    private NotificationSendingService notificationSendingService;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("test_email@gmail.com");

        Guest guest = new Guest();
        guest.setUser(user);

        reservation = new Reservation(
                1L,
                false,
                LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 4),
                3,
                3,
                300.0,
                ReservationStatus.ACCEPTED,
                guest,
                new Accommodation()
        );
    }

    @Test
    @DisplayName("Send notification")
    public void test_send_reservation_accepted_notification() {
        Notification notification = new Notification(reservation);

        doNothing().when(simpMessagingTemplate).convertAndSend(anyString(), any());
        when(notificationRepository.save(notification)).thenReturn(notification);

        notificationSendingService.send(notification);

        verify(notificationRepository).save(notification);
        verify(simpMessagingTemplate).convertAndSend(anyString(), any());
    }

}
