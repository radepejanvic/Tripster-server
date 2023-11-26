package com.tripster.project.repository;

import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccommodationReviewRepository extends JpaRepository<AccommodationReview, Long> {

    @Query("select r from AccommodationReview r " +
            "join fetch r.accommodation a " +
            "where a.id = :accommodationId")
    List<UserReview> findAllByAccommodationId(Long accommodationId);
}
