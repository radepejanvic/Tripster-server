package com.tripster.project.service;

import com.tripster.project.model.Amenity;
import com.tripster.project.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    public Set<Amenity> findByIdIn(List<Long> ids) {
        return amenityRepository.findByIdIn(ids);
    }



}
