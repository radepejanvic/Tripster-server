package com.tripster.project.service;

import com.tripster.project.model.*;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.DayStatus;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.management.relation.RelationServiceMBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReservationServiceTest {

    private final Long ACCOMMODATION_ID = 1L;

    private Accommodation accommodation;

    private Reservation reservation;

    @Autowired
    private ReservationServiceImpl reservationService;

    @MockBean
    private CalendarService calendarService;

    @MockBean
    private ReservationRepository reservationRepository;

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

        reservation = new Reservation(
                1L,
                LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 4),
                3,
                3,
                300.0,
                ReservationStatus.PENDING,
                new Guest(),
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
    }

    @Test
    @DisplayName("Accept reservation - no overlap")
    void test_accept_reservation_no_overlap() {
        when(calendarService.isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(true);
        when(reservationRepository.rejectOverlappingReservations(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(0);
        when(calendarService.reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(3);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        boolean accepted = reservationService.accept(reservation);

        assertTrue(accepted);

        verify(calendarService).isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).rejectOverlappingReservations(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(calendarService).reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).save(reservation);

        assertEquals(ReservationStatus.ACCEPTED, reservation.getStatus());
    }

    @Test
    @DisplayName("Accept reservation - with overlap")
    void test_accept_reservation_with_overlap() {
        when(calendarService.isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(true);
        when(reservationRepository.rejectOverlappingReservations(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(2);
        when(calendarService.reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd())).thenReturn(3);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        boolean accepted = reservationService.accept(reservation);

        assertTrue(accepted);

        verify(calendarService).isAvailable(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).rejectOverlappingReservations(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(calendarService).reserveDays(ACCOMMODATION_ID, reservation.getStart(), reservation.getEnd());
        verify(reservationRepository).save(reservation);

        assertEquals(ReservationStatus.ACCEPTED, reservation.getStatus());
    }


}
