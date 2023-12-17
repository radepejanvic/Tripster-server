package com.tripster.project.controller;

import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.mapper.ReviewDTOMapper;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Person;
import com.tripster.project.model.Review;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private UserService userService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @GetMapping(value = "/{accommodationId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long accommodationId) {
        List<AccommodationReview> reviews = accommodationReviewService.findAllByAccommodationId(accommodationId);

        Person reviewer;
        List<ReviewDTO> dtos = new ArrayList<>();
        for (Review review : reviews) {
            reviewer = guestService.findByUser(review.getReviewer());
            dtos.add(ReviewDTOMapper.fromReviewToDTO(review, reviewer.getName(), reviewer.getSurname()));
        }

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
