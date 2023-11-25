package com.tripster.project.repository;

import com.tripster.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
