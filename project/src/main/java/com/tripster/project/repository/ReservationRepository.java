package com.tripster.project.repository;

import com.tripster.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT res FROM Reservation res " +
            "JOIN FETCH res.accommodation " +
            "JOIN FETCH res.guest " +
            "WHERE res.guest.id = :guestId")
    public List<Reservation> getAllForGuest(Long guestId);
    @Query("SELECT res FROM Reservation res " +
            "JOIN FETCH res.accommodation " +
            "JOIN FETCH res.guest " +
            "WHERE res.guest.id = :guestId " +
            "and ( res.status = 'ACTIVE' or res.status = 'PENDING')")
    public List<Reservation> getAllActiveForGuest(Long guestId);
    @Query("SELECT res " +
            "FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "JOIN acc.owner owner " +
            "WHERE owner.id = :hostId")
    public List<Reservation> getAllForHost(Long hostId);

    @Query("SELECT res " +
            "FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "JOIN acc.owner owner " +
            "WHERE owner.id = :hostId " +
            "and (res.status = 'ACTIVE' or res.status = 'PENDING') ")
    public List<Reservation> getAllActiveForHost(Long hostId);

    @Query("SELECT res FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "WHERE  ((( :start < res.start and res.start < :end ) or (:start < res.end and res.end < :end)) " +
            "and acc.id = :accId) " +
            "and res.status != 'REJECTED' " +
            "and res.status != 'CANCELLED' ")
    public List<Reservation> getAllInDateRangeForAccommodation(LocalDate start, LocalDate end, Long accId);
    
}
