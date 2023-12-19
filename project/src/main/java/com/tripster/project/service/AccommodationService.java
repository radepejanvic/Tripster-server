package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void removeAllByOwnerId(Long ownerId) { accommodationRepository.deleteAllByOwnerId(ownerId);}
    public List<Accommodation> findFavorites(Long guestId) {
        return accommodationRepository.findFavorites(guestId);
    }

    public List<Accommodation> findByStatusIn(List<AccommodationStatus> statusList) {
        if (statusList == null || statusList.isEmpty()) {
            return findAll();
        }
        return accommodationRepository.findByStatusIn(statusList);
    };
    public List<Accommodation> findByStatusForApproval(List<AccommodationStatus> statusList) {
        if (statusList == null || statusList.isEmpty()) {
            return findAll();
        }
        List<AccommodationStatus> notIn = new ArrayList<>();
        notIn.add(AccommodationStatus.DELETED);
        notIn.add(AccommodationStatus.ACTIVE);
        notIn.add(AccommodationStatus.SUSPENDED);
        return accommodationRepository.findByStatusForApproval(statusList,notIn);
    };

    public List<Object[]> filterAll(String city, Long start, Long end, Integer numOfGuests, Set<Long> amenities, Double minPrice, Double maxPrice, AccommodationType type) {
        LocalDate startDate = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();

        Integer duration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

        Integer amenitiesSize;
        if (amenities == null) {
            amenitiesSize = 0;
        } else {
            amenitiesSize = amenities.size();
        }

        return accommodationRepository.filterAll(city, startDate, endDate, duration, numOfGuests, amenities, amenitiesSize, minPrice, maxPrice, type);
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

    public List<Day> findCalendar(Long accommodationId) {
        return accommodationRepository.findCalendar(accommodationId);
    }
}
