package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationReviewDTO;
import com.tripster.project.model.AccommodationReview;

public class AccommodationReviewDTOMapper {

    public static AccommodationReviewDTO fromAccommodationReviewToDTO(AccommodationReview review) {
        AccommodationReviewDTO dto = new AccommodationReviewDTO();
        dto.setId(review.getId());
        dto.setRate(review.getRate());
        dto.setComment(review.getComment());
        dto.setReviewerId(review.getReviewer().getId());
        return dto;
    }

    public static AccommodationReview fromDTOToAccommodationReview(AccommodationReviewDTO dto) {
        AccommodationReview review = new AccommodationReview();
        review.setId(dto.getId());
        review.setRate(dto.getRate());
        review.setComment(dto.getComment());
        return review;
    }

}
