package com.tripster.project.service;

import com.tripster.project.model.Reservation;
import com.tripster.project.repository.ReservationRepository;
import com.tripster.project.service.interfaces.IReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationServiceImpl {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation findOne(Long id) {
        Reservation res = reservationRepository.findById(id).orElseGet(null);
        return res;
    }
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    public Page<Reservation> findAll(Pageable page ) {
        return reservationRepository.findAll(page);
    }
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public void remove(Long id) { reservationRepository.deleteById(id); }
    public List<Reservation> getAllForGuest(Long guestId) {
        return reservationRepository.getAllForGuest(guestId);
    }
    public List<Reservation> getAllForHost(Long hostId) {
        return reservationRepository.getAllForHost(hostId);
    }
    public List<Reservation> getAllInDateRange(LocalDate start, LocalDate end) {
        return reservationRepository.getAllInDateRange(start, end);
    }
}
