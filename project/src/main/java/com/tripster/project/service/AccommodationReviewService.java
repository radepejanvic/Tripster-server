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

    public RatingStatsDTO countTotalStats(Long accommodationId) {
        List<Object[]> stats = accommodationReviewRepository.countTotalStats(accommodationId);
        List<Object[]> total = accommodationReviewRepository.countReviews(accommodationId);

        RatingStatsDTO rating = new RatingStatsDTO();
        rating.setRating((double)total.get(0)[0]);
        rating.setReviews((long)total.get(0)[1]);

        for (Object[] stat : stats) {
            mapStatsToRating(rating, stat);
        }

        return rating;
    };

    private void mapStatsToRating(RatingStatsDTO rating, Object[] stat) {
        int rate = (int)stat[0];
        long count = (long)stat[1];

        switch(rate){
            case 1: rating.setBad(count); break;
            case 2: rating.setPoor(count); break;
            case 3: rating.setAverage(count); break;
            case 4: rating.setGood(count); break;
            case 5: rating.setExcellent(count); break;
        }
    }


    public List<AccommodationReview> findAllNew() {
        return accommodationReviewRepository.findAllNew();
    }

}
