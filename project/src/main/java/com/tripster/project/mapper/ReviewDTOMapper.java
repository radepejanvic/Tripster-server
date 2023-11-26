package com.tripster.project.mapper;

import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.Review;
import com.tripster.project.model.UserReview;

public class ReviewDTOMapper {

    public static ReviewDTO fromReviewToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRate(review.getRate());
        dto.setComment(review.getComment());
        dto.setStatus(review.getStatus());
        dto.setReviewerId(review.getReviewer().getId());
        return dto;
    }

    public static AccommodationReview fromDTOToAccommodationReview(ReviewDTO dto) {
        AccommodationReview review = new AccommodationReview();
        review.setId(dto.getId());
        review.setRate(dto.getRate());
        review.setComment(dto.getComment());
        review.setStatus(dto.getStatus());
        return review;
    }

    public static UserReview fromDTOToUserReview(ReviewDTO dto) {
        UserReview review = new UserReview();
        review.setId(dto.getId());
        review.setRate(dto.getRate());
        review.setComment(dto.getComment());
        review.setStatus(dto.getStatus());
        return review;
    }

}
