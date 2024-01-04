package com.tripster.project.repository;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT res FROM Reservation res " +
            "JOIN FETCH res.accommodation " +
            "JOIN FETCH res.guest " +
            "WHERE res.guest.id = :guestId")
    List<Reservation> getAllForGuest(Long guestId);
    @Query("SELECT res FROM Reservation res " +
            "JOIN FETCH res.accommodation " +
            "JOIN FETCH res.guest " +
            "WHERE res.guest.id = :guestId " +
            "and ( res.status = 'ACTIVE' or res.status = 'PENDING')")
    List<Reservation> getAllActiveForGuest(Long guestId);
    @Query("SELECT res " +
            "FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "JOIN acc.owner owner " +
            "WHERE owner.id = :hostId")
    List<Reservation> getAllForHost(Long hostId);

    @Query("SELECT res " +
            "FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "JOIN acc.owner owner " +
            "WHERE owner.id = :hostId " +
            "and (res.status = 'ACTIVE' or res.status = 'PENDING') ")
    List<Reservation> getAllActiveForHost(Long hostId);

    @Query("SELECT res FROM Reservation res " +
            "JOIN res.accommodation acc " +
            "WHERE  ((( :start < res.start and res.start < :end ) or (:start < res.end and res.end < :end)) " +
            "and acc.id = :accId) " +
            "and res.status != 'REJECTED' " +
            "and res.status != 'CANCELLED' ")
    List<Reservation> getAllInDateRangeForAccommodation(LocalDate start, LocalDate end, Long accId);

    @Query("select count(r) " +
            "from Reservation r " +
            "join r.accommodation a " +
            "join r.guest g " +
            "where a.id = :accommodationId " +
            "and g.id = :guestId " +
            "and r.status = 'ACCEPTED' " +
            "and r.end between :pastSevenDays and :today")
    int findAllByGuestAndAccommodation(Long accommodationId, Long guestId, LocalDate today, LocalDate pastSevenDays);

    @Query("select count(r) " +
            "from Reservation r " +
            "join r.accommodation a " +
            "join a.owner h " +
            "join r.guest g " +
            "where h.id = :hostId " +
            "and g.id = :guestId " +
            "and r.status = 'ACCEPTED' " +
            "and r.end <= :today")
    int findAllByGuestAndHost(Long hostId, Long guestId, LocalDate today);

    @Query("select r " +
            "from Reservation r " +
            "join r.guest g " +
            "join r.accommodation a " +
            "where g.id = :guestId " +
            "and (:name = null or upper(a.name) like :name )" +
            "and (:statusList = null or r.status in :statusList)" +
            "and (cast(:start as date) is null or r.start >= :start) " +
            "and (cast(:end as date) is null or r.end <= :end) " )
    List<Reservation> findByGuestFilter(Long guestId,String name,LocalDate start,LocalDate end,List<ReservationStatus> statusList);

    @Query("select r " +
            "from Reservation r " +
            "join r.accommodation a " +
            "join a.owner o " +
            "where o.id = :hostId " +
            "and (:name = null or upper(a.name) like :name )" +
            "and (:statusList = null or r.status in :statusList)" +
            "and (cast(:start as date) is null or r.start >= :start) " +
            "and (cast(:end as date) is null or r.end <= :end) " )
    List<Reservation> findByHostFilter(Long hostId,String name,LocalDate start,LocalDate end,List<ReservationStatus> statusList);
}
