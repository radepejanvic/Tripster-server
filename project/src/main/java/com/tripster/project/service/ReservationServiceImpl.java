package com.tripster.project.service;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.repository.ReservationRepository;
import com.tripster.project.service.interfaces.IReservationServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CalendarService calendarService;

    public Reservation findOne(Long id) {
        return reservationRepository.findById(id).orElse(null);
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

    @Transactional
    public boolean accept(Reservation reservation) {

        if (reservation.getAccommodation().isAutomaticReservation() || reservation.getStatus() != ReservationStatus.PENDING) {
            return false;
        }

        if (!calendarService.isAvailable(reservation.getAccommodation().getId(), reservation.getStart(), reservation.getEnd())) {
            return false;
        }

        // TODO: Call sendNotification method, for each of reservations in getAllInDateRangeForAccommodation
        int rejected = reservationRepository.rejectOverlappingReservations(reservation.getAccommodation().getId(), reservation.getStart(), reservation.getEnd());

        reservation.setStatus(ReservationStatus.ACCEPTED);
        calendarService.reserveDays(reservation.getAccommodation().getId(), reservation.getStart(), reservation.getEnd());

        // TODO: Call sendNotification method

        reservationRepository.save(reservation);
        return true;
    }

}
