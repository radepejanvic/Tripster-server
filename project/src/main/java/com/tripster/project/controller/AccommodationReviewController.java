package com.tripster.project.controller;

import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.mapper.ReviewDTOMapper;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/accommodations/reviews")
public class AccommodationReviewController {

    @Autowired
    private AccommodationReviewService accommodationReviewService;
    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{accommodationId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long accommodationId) {
        List<AccommodationReview> reviews = accommodationReviewService.findAllByAccommodationId(accommodationId);

        List<ReviewDTO> dtos = reviews.stream()
                .map(ReviewDTOMapper::fromReviewToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> saveAccommodation(@RequestBody ReviewDTO dto) {

        AccommodationReview review = ReviewDTOMapper.fromDTOToAccommodationReview(dto);
        review.setReviewer(userService.findOne(dto.getReviewerId()));
        review.setAccommodation(accommodationService.findOne(dto.getReviewedId()));
        accommodationReviewService.save(review);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {

        AccommodationReview review = accommodationReviewService.findOne(id);

        if (review != null) {
            accommodationReviewService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
