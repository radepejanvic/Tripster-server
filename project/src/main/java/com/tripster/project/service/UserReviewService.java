package com.tripster.project.service;
import com.tripster.project.model.UserReview;
import com.tripster.project.repository.UserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewService {

    @Autowired
    private UserReviewRepository userReviewRepository;

    public UserReview findOne(Long id) {
        return userReviewRepository.findById(id).orElseGet(null);
    }

    public List<UserReview> findAll() {
        return userReviewRepository.findAll();
    }

    public Page<UserReview> findAll(Pageable page) {
        return userReviewRepository.findAll(page);
    }

    public UserReview save(UserReview review) {
        return  userReviewRepository.save(review);
    }

    public void remove(Long id) {
        userReviewRepository.deleteById(id);
    }

    public List<UserReview> findAllByReviewedId(Long reviewedId) {
        return userReviewRepository.findAllByReviewedId(reviewedId);
    }

    public List<Object[]> countReviews(Long reviewedId) {
        return userReviewRepository.countReviews(reviewedId);
    }
}
