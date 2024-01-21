package com.tripster.project.service;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import com.tripster.project.service.interfaces.IReservationServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public Reservation findOne(Long id) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.findById(id).orElseGet(null);
    }

    @Transactional
    public List<Reservation> findAll() {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.findAll();
    }

    @Transactional
    public Page<Reservation> findAll(Pageable page ) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.findAll(page);
    }

    @Transactional
    public Reservation save(Reservation reservation) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void remove(Long id) { reservationRepository.deleteById(id); }
    public List<Reservation> getAllForGuest(Long guestId) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.getAllForGuest(guestId);
    }

    @Transactional
    public List<Reservation> getAllActiveForGuest(Long guestId) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.getAllActiveForGuest(guestId); }

    @Transactional
    public List<Reservation> getAllForHost(Long hostId) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.getAllForHost(hostId);
    }

    @Transactional
    public List<Reservation> getAllActiveForHost(Long hostId) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.getAllActiveForHost(hostId);}

    @Transactional
    public List<Reservation> getAllInDateRangeForAccommodation(LocalDate start, LocalDate end, Long accId) {
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.getAllInDateRangeForAccommodation(start, end, accId);
    }

    @Override
    @Transactional
    public List<Reservation> findByGuestFilter(Long id, String name, long start, long end, List<ReservationStatus> statusList) {
        LocalDate startDate = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();
        if (name != null){
            name = "%" + name.toUpperCase() + "%";
        }
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.findByGuestFilter(id,name,startDate,endDate,statusList);

    }

    @Override
    @Transactional
    public List<Reservation> findByHostFilter(Long id, String name, long start, long end, List<ReservationStatus> statusList) {
        LocalDate startDate = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();
        if (name != null){
            name = "%" + name.toUpperCase() + "%";
        }
        reservationRepository.markAsPassed(LocalDate.now());
        return reservationRepository.findByHostFilter(id,name,startDate,endDate,statusList);

    }

    public int calculateNumberOfCancelled(Long guestId) {
        return reservationRepository.calculateNumberOfCancelled(guestId);
    }
}
