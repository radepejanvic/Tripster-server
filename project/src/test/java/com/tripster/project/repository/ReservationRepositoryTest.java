package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ReservationRepositoryTest {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @DisplayName("Reject overlapping reservations")
    public void test_reject_overlapping_reservations() {

        Reservation res1 = reservationRepository.findById(7L).get();

        int rejected = reservationRepository.rejectOverlappingReservations(7L, 1L, res1.getStart(), res1.getEnd());

        res1 = reservationRepository.findById(7L).get();
        Reservation res2 = reservationRepository.findById(8L).get();
        Reservation res3 = reservationRepository.findById(9L).get();
        Reservation res4 = reservationRepository.findById(10L).get();
        Reservation res5 = reservationRepository.findById(11L).get();

        Reservation res6 = reservationRepository.findById(2L).get();
        Reservation res7 = reservationRepository.findById(3L).get();
        Reservation res8 = reservationRepository.findById(4L).get();
        Reservation res9 = reservationRepository.findById(5L).get();

        assertEquals(4, rejected);
        assertEquals(ReservationStatus.PENDING, res1.getStatus());
        assertEquals(ReservationStatus.REJECTED, res2.getStatus());
        assertEquals(ReservationStatus.REJECTED, res3.getStatus());
        assertEquals(ReservationStatus.REJECTED, res4.getStatus());
        assertEquals(ReservationStatus.REJECTED, res5.getStatus());
        assertEquals(ReservationStatus.ACCEPTED, res6.getStatus());
        assertEquals(ReservationStatus.ACCEPTED, res7.getStatus());
        assertEquals(ReservationStatus.CANCELLED, res8.getStatus());
        assertEquals(ReservationStatus.REJECTED, res9.getStatus());
    }
    
    
    
}
