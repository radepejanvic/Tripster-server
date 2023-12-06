package com.tripster.project.repository;

import com.tripster.project.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p" +
            " from Photo p" +
            " where p.accommodationId = :accommodationId")
    List<Photo> findByAccommodationId(Long accommodationId);

}
