package com.tripster.project.repository;

import com.tripster.project.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    @Query("select r from UserReview r " +
            "join fetch r.reviewedUser u " +
            "where u.id = :reviewedId")
    List<UserReview> findAllByReviewedId(Long reviewedId);
}
