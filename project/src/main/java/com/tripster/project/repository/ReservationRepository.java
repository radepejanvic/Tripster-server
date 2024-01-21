package com.tripster.project.repository;

import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
            "WHERE  ((( :start < res.start AND res.start < :end ) OR (:start < res.end AND res.end < :end)) " +
            "AND acc.id = :accId) " +
            "AND res.status != 'REJECTED' " +
            "AND res.status != 'CANCELLED' ")
    List<Reservation> getAllInDateRangeForAccommodation(LocalDate start, LocalDate end, Long accId);

    @Modifying
    @Query("update Reservation r " +
            "set r.status = 'REJECTED' " +
            "where r.id != :id " +
            "and r.accommodation.id = :accommodationId " +
            "and r.status = 'PENDING' " +
            "and (:start <= r.end and :end >= r.start)")
    int rejectOverlappingReservations(Long id, Long accommodationId, LocalDate start, LocalDate end);

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

    @Query("select a.id, a.name, month(r.start), year(r.start), count(r), sum(r.price)" +
            "from Reservation r " +
            "join r.accommodation a " +
            "join a.owner o " +
            "where o.id = :hostId " +
            "and r.status = 'ACCEPTED' " +
            "and year(r.start) = :year " +
            "group by a.id, a.name, month(r.start), year(r.start) " +
            "order by a.id, month(r.start)")
    List<Object[]> calculateAnnualAnalytics(Long hostId, int year);

    @Query("select r.status, count(r), sum(r.price)" +
            "from Reservation r " +
            "join r.accommodation a " +
            "join a.owner o " +
            "where o.id = :hostId " +
            "and r.status != 'DELETED' " +
            "and r.start between :start and :end " +
            "group by r.status")
    List<Object[]> calculateTotalAnalyticsPerStatus(Long hostId, LocalDate start, LocalDate end);

    @Query("select a.id, a.name, count(r), sum(r.price)" +
            "from Reservation r " +
            "join r.accommodation a " +
            "join a.owner o " +
            "where o.id = :hostId " +
            "and r.status = 'ACCEPTED' " +
            "and r.start between :start and :end " +
            "group by a.id, a.name")
    List<Object[]> calculateTotalAnalyticsPerAccommodation(Long hostId, LocalDate start, LocalDate end);


    @Query("select count(r) " +
            "from Reservation r " +
            "where r.guest.id = :guestId " +
            "and r.status = 'CANCELLED'")
    int calculateNumberOfCancelled(Long guestId);

}
