package com.tripster.project.service;

import com.tripster.project.model.*;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReservationServiceTest {

    private final Long ACCOMMODATION_ID = 1L;
    private final Long RESERVATION_ID = 1L;

    private Accommodation accommodation;

    private Reservation reservation;

    @Autowired
    private ReservationServiceImpl reservationService;

    @MockBean
    private CalendarService calendarService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private NotificationSendingService notificationSendingService;

    @BeforeEach
    public void setUp() {
        accommodation = new Accommodation(
                ACCOMMODATION_ID,
                "Test Accommodation",
                new Host(),
                new Address(),
                "Short description",
                "Complete accommodation description",
                new HashSet<>(),
                1,
                5,
                14,
                AccommodationType.APARTMENT,
                false,
                AccommodationStatus.ACTIVE,
                LocalDateTime.now(),
                new HashSet<>(),
                true
        );

        User user = new User();
        user.setEmail("test_email@gmail.com");

        Guest guest = new Guest();
        guest.setUser(user);

        reservation = new Reservation(
                RESERVATION_ID,
                LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 4),
                3,
                3,
                300.0,
                ReservationStatus.PENDING,
                guest,
                accommodation
        );

    }

    @Test
    @DisplayName("Accept reservation - accommodation with automatic reservation")
    void test_accept_reservation_automatic_reservation_accommodation() {
        accommodation.setAutomaticReservation(true);

        boolean accepted = reservationService.accept(reservation);

        assertFalse(accepted);

        verifyNoInteractions(reservationRepository);
        verifyNoInteractions(calendarService);
        verifyNoInteractions(reservationRepository);
        verifyNoInteractions(notificationSendingService);
    }

    @ParameterizedTest
    @DisplayName("Accept reservation - reservation status not PENDING")
    @CsvSource(value = {"ACCEPTED", "CANCELLED", "REJECTED"})
    void test_accept_reservation_status_not_pending(String status) {
        reservation.setStatus(ReservationStatus.valueOf(status));

        boolean accepted = reservationService.accept(reservation);

        assertFalse(accepted);

        verifyNoInteractions(reservationRepository);
        verifyNoInteractions(calendarService);
        verifyNoInteractions(reservationRepository);
        verifyNoInteractions(notificationSendingService);
    }

    @Test
    @DisplayName("Accept reservation - accommodation not available")
    void test_accept_reservation_accommodation_not_available() {
        when(calendarService.isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(false);

        boolean accepted = reservationService.accept(reservation);

        assertFalse(accepted);

        verify(calendarService).isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());

        verifyNoInteractions(reservationRepository);
        verifyNoMoreInteractions(calendarService);
        verifyNoInteractions(reservationRepository);
        verifyNoInteractions(notificationSendingService);
    }

    @Test
    @DisplayName("Accept reservation - no overlap")
    void test_accept_reservation_no_overlap() {
        when(calendarService.isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(true);
        when(reservationRepository.rejectOverlappingReservations(RESERVATION_ID, ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(0);
        when(calendarService.reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(3);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        doNothing().when(notificationSendingService).send(any());

        boolean accepted = reservationService.accept(reservation);

        assertTrue(accepted);

        verify(calendarService).isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).rejectOverlappingReservations(RESERVATION_ID, ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(calendarService).reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).save(reservation);
        verify(notificationSendingService).send(any());

        assertEquals(ReservationStatus.ACCEPTED, reservation.getStatus());
    }

    @Test
    @DisplayName("Accept reservation - with overlap")
    void test_accept_reservation_with_overlap() {
        when(calendarService.isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(true);
        when(reservationRepository.rejectOverlappingReservations(RESERVATION_ID, ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(2);
        when(calendarService.reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(3);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        boolean accepted = reservationService.accept(reservation);

        assertTrue(accepted);

        verify(calendarService).isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).rejectOverlappingReservations(RESERVATION_ID, ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(calendarService).reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).save(reservation);
        verify(notificationSendingService).send(any());

        assertEquals(ReservationStatus.ACCEPTED, reservation.getStatus());
    }


}
