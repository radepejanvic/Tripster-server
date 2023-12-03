package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Accommodation findOne(Long id) {
        return accommodationRepository.findById(id).orElseGet(null);
    }

    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Page<Accommodation> findAll(Pageable page) {
        return accommodationRepository.findAll(page);
    }

    public Accommodation save(Accommodation Accommodation) {
        return accommodationRepository.save(Accommodation);
    }

    public void remove(Long id) {
        accommodationRepository.deleteById(id);
    }

    public List<Accommodation> findAllByOwnerId(Long id) {
        return accommodationRepository.findAllByOwnerId(id);
    }

    public List<Accommodation> findFavorites(Long guestId) {
        return accommodationRepository.findFavorites(guestId);
    }

    public List<Accommodation> findByStatusIn(List<AccommodationStatus> statusList) {
        if (statusList == null || statusList.isEmpty()) {
            return findAll();
        }
        return accommodationRepository.findByStatusIn(statusList);
    };

    public List<Object[]> filterAll(String city, String start, String end, Integer numOfGuests) {
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter).minusDays(1);

        Integer duration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

        return accommodationRepository.filterAll(city, startDate, endDate, duration, numOfGuests);
    }


    public void generateCalendar(LocalDate startDate, Accommodation accommodation) {

        HashSet<Day> calendar;

        if (accommodation.getCalendar() != null) {
            calendar = (HashSet<Day>) accommodation.getCalendar();
        } else {
            calendar = new HashSet<>();
        }

        LocalDate date;
        LocalDate endDate = startDate.plusYears(1);
        while(!startDate.isAfter(endDate)) {
            date = startDate;
            calendar.add(new Day(null, date, 10.0, true));
            startDate = startDate.plusDays(1);
        }

        accommodation.setCalendar(calendar);
//        save(accommodation);
    }
}
