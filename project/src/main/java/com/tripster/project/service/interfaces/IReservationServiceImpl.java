package com.tripster.project.service.interfaces;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;

import java.util.List;

public interface IReservationServiceImpl {
    List<Reservation> findByGuestFilter(Long id, String name, long l, long l1, List<ReservationStatus> statusList);

    List<Reservation> findByHostFilter(Long id, String name, long start, long end, List<ReservationStatus> statusList);
}
