package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.enums.AccommodationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query(value = "select a.id, count(d.date) , sum(d.price)"+
            " from Accommodation a " +
            " join a.calendar d" +
            " join a.address ad" +
            " where (:city = null or ad.city = :city)" +
            " and (:numOfGuests = null or :numOfGuests between a.minCap and a.maxCap)" +
            " and d.date between :start and :end" +
            " and d.isAvailable = true " +
            " group by a.id " +
            " having count(d.date)")
    List<Object[]> filterAll(String city, LocalDate start, LocalDate end, Integer duration, Integer numOfGuests);

    @Query("select a " +
            " from Accommodation a" +
            " join fetch a.owner o" +
            " where o.id = :ownerId")
    List<Accommodation> findAllByOwnerId(Long ownerId);


    List<Accommodation> findByStatusIn(List<AccommodationStatus> statusList);

    @Query("select a" +
            " from Guest g" +
            " join fetch Accommodation a" +
            " where g.id = :guestId")
    List<Accommodation> findFavorites(Long guestId);

}
