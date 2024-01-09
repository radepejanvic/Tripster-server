package com.tripster.project.service;

import com.tripster.project.dto.RatingStatsDTO;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.UserReview;
import com.tripster.project.repository.AccommodationReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationReviewService {

    @Autowired
    private AccommodationReviewRepository accommodationReviewRepository;

    public AccommodationReview findOne(Long id) {
        return accommodationReviewRepository.findById(id).orElseGet(null);
    }

    public List<AccommodationReview> findAll() {
        return accommodationReviewRepository.findAll();
    }

    public Page<AccommodationReview> findAll(Pageable page ) {
        return accommodationReviewRepository.findAll(page);
    }

    public AccommodationReview save(AccommodationReview review) {
        return  accommodationReviewRepository.save(review);
    }

    public void remove(Long id) {
        accommodationReviewRepository.deleteById(id);
    }

    public List<AccommodationReview> findAllByAccommodationId(Long accommodationId) {
        return accommodationReviewRepository.findAllByAccommodationId(accommodationId);
    }
    public List<Object[]> countReviews(Long accommodationId){
        return accommodationReviewRepository.countReviews(accommodationId);
    }

    public RatingStatsDTO countTotalStats(Long acommodationId) {
        List<Object[]> stats = accommodationReviewRepository.countTotalStats(acommodationId);
        List<Object[]> total = accommodationReviewRepository.countReviews(acommodationId);

        return new RatingStatsDTO((double)total.get(0)[0],
                (long)total.get(0)[1],
                (long)stats.get(0)[1],
                (long)stats.get(1)[1],
                (long)stats.get(2)[1],
                (long)stats.get(3)[1],
                (long)stats.get(4)[1]);
    };

    public List<AccommodationReview> findAllNew() {
        return accommodationReviewRepository.findAllNew();
    }

}
