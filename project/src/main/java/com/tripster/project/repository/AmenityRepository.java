package com.tripster.project.repository;

import com.tripster.project.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    Set<Amenity> findByIdIn(List<Long> ids);

}
