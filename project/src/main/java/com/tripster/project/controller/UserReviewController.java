package com.tripster.project.controller;

import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.mapper.ReviewDTOMapper;
import com.tripster.project.model.UserReview;
import com.tripster.project.service.UserReviewService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/users/reviews")
public class UserReviewController {

    @Autowired
    private UserReviewService userReviewService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long userId) {
        List<UserReview> reviews = userReviewService.findAllByReviewedId(userId);

        List<ReviewDTO> dtos = reviews.stream()
                .map(ReviewDTOMapper::fromReviewToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> saveAccommodation(@RequestBody ReviewDTO dto) {

        UserReview review = ReviewDTOMapper.fromDTOToUserReview(dto);
        review.setReviewer(userService.findOne(dto.getReviewerId()));
        review.setReviewedUser(userService.findOne(dto.getReviewedId()));
        userReviewService.save(review);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {

        UserReview review = userReviewService.findOne(id);

        if (review != null) {
            userReviewService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
