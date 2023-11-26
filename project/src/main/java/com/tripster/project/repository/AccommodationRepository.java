package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("select a, sum(d.price) as totalPrice, count(d) as totalDays" +
            " from Accommodation a join Day d on d.accommodation = a" +
            " where :numOfGuests between a.minCap and a.maxCap and d.date between :start and :end and d.isAvailable = true" +
            " group by a" +
            " having totalDays = datediff(:start, :end)")
    Collection<Object[]> findAllAvailableAccommodationsWithPrice(LocalDate start, LocalDate end, int numOfGuests);

    List<Accommodation> findAllByOwner(Host host);

}
