package com.tripster.project.repository;

import com.tripster.project.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findAllByAccommodationId(Long accommodationId);

}
