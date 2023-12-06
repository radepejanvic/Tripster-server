package com.tripster.project.repository;

import com.tripster.project.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p" +
            " from Photo p" +
            " join p.accommodation a" +
            " where a.id = :accommodationId")
    List<Photo> findAllByAccommodationId(Long accommodationId);

}
