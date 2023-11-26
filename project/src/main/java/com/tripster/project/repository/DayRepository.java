package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface DayRepository extends JpaRepository<Day, Long> {

    @Query("select sum(d.price) from Day d where d.accommodation = :accommodation and d.date between :startDate and :endDate")
    double sumPriceBetweenDates(Accommodation accommodation, LocalDate startDate, LocalDate endDate);
}
