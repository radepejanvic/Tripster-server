package com.tripster.project.service;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import com.tripster.project.service.interfaces.IReservationServiceImpl;
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

    public Reservation findOne(Long id) {
        return reservationRepository.findById(id).orElseGet(null);
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
    public List<Reservation> getAllActiveForGuest(Long guestId) { return reservationRepository.getAllActiveForGuest(guestId); }
    public List<Reservation> getAllForHost(Long hostId) {
        return reservationRepository.getAllForHost(hostId);
    }
    public List<Reservation> getAllActiveForHost(Long hostId) { return reservationRepository.getAllActiveForHost(hostId);}

    @Override
    public List<Reservation> findByGuestFilter(Long id, String name, long start, long end, List<ReservationStatus> statusList) {
        LocalDate startDate = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();
        if (name != null){
            name = "%" + name.toUpperCase() + "%";
        }
        return reservationRepository.findByGuestFilter(id,name,startDate,endDate,statusList);

    }

    @Override
    public List<Reservation> findByHostFilter(Long id, String name, long start, long end, List<ReservationStatus> statusList) {
        LocalDate startDate = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();
        if (name != null){
            name = "%" + name.toUpperCase() + "%";
        }
        return reservationRepository.findByHostFilter(id,name,startDate,endDate,statusList);

    }

    public int calculateNumberOfCancelled(Long guestId) {
        return reservationRepository.calculateNumberOfCancelled(guestId);
    }

    public List<Reservation> getAllInDateRangeForAccommodation(LocalDate start, LocalDate end, Long accId) {
        return reservationRepository.getAllInDateRangeForAccommodation(start, end, accId);
    }

    public int rejectOverlappingReservations(Long accommodationId, LocalDate start, LocalDate end) {
        return reservationRepository.rejectOverlappingReservations(accommodationId, start, end);
    }

}
