package com.tripster.project.service;

import com.tripster.project.model.Reservation;

import java.util.Collection;

public interface ReservationService {
    Collection<Reservation> findAll();
    Reservation findOne(Long id);
    Reservation create(Reservation collection) throws Exception;
    Reservation update(Reservation reservation) throws Exception;
    void delete(Long id);

}
