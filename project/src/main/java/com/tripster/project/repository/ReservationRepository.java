package com.tripster.project.repository;

import com.tripster.project.model.Reservation;

import java.util.Collection;

public interface ReservationRepository {
    Collection<Reservation> findAll();
    Reservation create(Reservation reservation);
    Reservation findOne(Long id);
    Reservation update(Reservation reservation);
    void delete(Long id);
}
