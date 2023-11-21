package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
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

    public Accommodation findOne(Integer id) {
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

    public void remove(Integer id) {
        accommodationRepository.deleteById(id);
    }
}
