package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.model.enums.DayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query(value = "select a.id, count(d.date) , sum(d.price)"+
            " from Accommodation a " +
            " join a.calendar d" +
            " join a.address ad" +
            " where (:city = null or ad.city = :city)" +
            " and (:numOfGuests = null or :numOfGuests between a.minCap and a.maxCap)" +
            " and (:type = null or a.type = :type)" +
            " and d.date between :start and :end" +
            " and d.availability = :available" +
            " and (:amenitiesSize = 0 or :amenitiesSize = (select count(am.id) from a.amenities am where am.id in :amenities))" +
            " group by a.id" +
            " having count(d.date) = :duration" +
            " and (:minPrice = null or sum(d.price) >= :minPrice)" +
            " and (:maxPrice = null or sum(d.price) <= :maxPrice)")
    List<Object[]> filterAll(String city, LocalDate start, LocalDate end, Integer duration, Integer numOfGuests, Set<Long> amenities, Integer amenitiesSize, Double minPrice, Double maxPrice, AccommodationType type, DayStatus available);

    @Query("select a " +
            " from Accommodation a" +
            " join fetch a.owner o" +
            " where o.id = :ownerId")
    List<Accommodation> findAllByOwnerId(Long ownerId);


    List<Accommodation> findByStatusIn(List<AccommodationStatus> statusList);

    @Query("select a " +
            "from Accommodation a " +
            "where a.status in :statusList and a.status not in :statusNotIn")
    List<Accommodation> findByStatusForApproval(List<AccommodationStatus> statusList,List<AccommodationStatus> statusNotIn);

    @Query("select a" +
            " from Guest g" +
            " join fetch Accommodation a" +
            " where g.id = :guestId")
    List<Accommodation> findFavorites(Long guestId);

    @Query("select d from" +
            " Accommodation a join" +
            " a.calendar d" +
            " where a.id = :id" +
            " and d.availability = :available" +
            " order by d.date")
    List<Day> findCalendar(Long id, DayStatus available);
}
