package com.tripster.project.repository;

import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccommodationReviewRepository extends JpaRepository<AccommodationReview, Long> {

    @Query("select r from AccommodationReview r " +
            "join fetch r.accommodation a " +
            "where a.id = :accommodationId " +
            "and (r.status = 'ACTIVE' or r.status = 'REPORTED')")
    List<AccommodationReview> findAllByAccommodationId(Long accommodationId);

    @Query("select coalesce(round(avg(r.rate),2), 0), coalesce(count(r), 0)" +
            " from AccommodationReview r" +
            " join r.accommodation a" +
            " where a.id = :accommodationId" +
            " and (r.status = 'ACTIVE' or r.status = 'REPORTED') ")
    List<Object[]> countReviews(Long accommodationId);

    @Query("select r.rate, count(r)" +
            "from AccommodationReview r " +
            "join r.accommodation a " +
            "where a.id = :accommodationId " +
            "and (r.status = 'ACTIVE' or r.status = 'REPORTED') " +
            "group by r.rate " +
            "order by r.rate desc")
    List<Object[]> countTotalStats(Long accommodationId);

    @Query("select r " +
            "from AccommodationReview r " +
            "where r.status = 'NEW'")
    List<AccommodationReview> findAllNew();
}
