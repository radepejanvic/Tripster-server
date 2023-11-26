package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Host;
import com.tripster.project.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

//    public Collection<Object[]> findAllAvailableAccommodationsWithPrice(LocalDate start, LocalDate end, int numOfGuests) {
//        return accommodationRepository.findAllAvailableAccommodationsWithPrice(start, end, numOfGuests);
//    }

    public List<Accommodation> findAllByOwner(Host host) {
        return accommodationRepository.findAllByOwner(host);
    }
}
