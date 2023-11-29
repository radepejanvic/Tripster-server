package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Day;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

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
            calendar.add(new Day(null, date, 0.0, true));
            startDate = startDate.plusDays(1);
        }

        accommodation.setCalendar(calendar);
        save(accommodation);
    }
}
