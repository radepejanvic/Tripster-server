package com.tripster.project.service;

import com.tripster.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewService {

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean canReviewHost(Long hostId, Long guestId) {

        LocalDate today = LocalDate.now();

        int count = reservationRepository.findAllByGuestAndHost(hostId, guestId, today);

        return count != 0;
    }

    public boolean canReviewAccommodation(Long accommodationId, Long guestId) {

        LocalDate today = LocalDate.now();
        LocalDate pastSevenDays = today.minusDays(7);

        int count = reservationRepository.findAllByGuestAndAccommodation(accommodationId, guestId, today, pastSevenDays);

        return count != 0;
    }

}
