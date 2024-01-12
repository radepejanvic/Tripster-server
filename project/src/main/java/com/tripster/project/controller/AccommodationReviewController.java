package com.tripster.project.controller;

import com.tripster.project.dto.RatingStatsDTO;
import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.dto.StatusDTO;
import com.tripster.project.mapper.ReviewDTOMapper;
import com.tripster.project.model.*;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.ReviewStatus;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.NotificationSendingService;
import com.tripster.project.service.ReviewService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.PhotoService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private ReviewService reviewService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private NotificationSendingService notificationSendingService;


    @GetMapping("/new")
    public ResponseEntity<List<ReviewDTO>> getAllNew() {
        List<AccommodationReview> reviews = accommodationReviewService.findAllNew();

        List<ReviewDTO> dtos = new ArrayList<>();
        for (AccommodationReview review : reviews) {
            Person reviewer = guestService.findByUser(review.getReviewer());

            dtos.add(ReviewDTOMapper.fromReviewToApproveDTO(review,
                    reviewer.getName(),
                    reviewer.getSurname(),
                    review.getAccommodation().getName(),
                    photoService.findPrimary(review.getAccommodation().getId())));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

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

    @GetMapping(value = "/{accommodationId}/{guestId}")
    public ResponseEntity<Boolean> canReviewAccommodation(@PathVariable Long accommodationId, @PathVariable Long guestId) {
        return new ResponseEntity<>(reviewService.canReviewAccommodation(accommodationId, guestId), HttpStatus.OK);
    }

    @GetMapping(value = "/stats/{accommodationId}")
    public ResponseEntity<RatingStatsDTO> getStats(@PathVariable Long accommodationId) {
        return new ResponseEntity<>(accommodationReviewService.countTotalStats(accommodationId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> saveAccommodation(@RequestBody ReviewDTO dto) {

        AccommodationReview review = ReviewDTOMapper.fromDTOToAccommodationReview(dto);
        review.setReviewer(userService.findOne(dto.getReviewerId()));
        review.setAccommodation(accommodationService.findOne(dto.getReviewedId()));
        review.setTimeStamp(LocalDateTime.now());
        review.setStatus(ReviewStatus.NEW);
        accommodationReviewService.save(review);

        notificationSendingService.send(new Notification(review));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(consumes = "application/json")
    public ResponseEntity<String> approveReview(@RequestBody StatusDTO dto) {

        AccommodationReview review = accommodationReviewService.findOne(dto.getId());
        review.setStatus(ReviewStatus.valueOf(dto.getStatus()));
        accommodationReviewService.save(review);

        return new ResponseEntity<>(dto.getStatus(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAccommodation(@PathVariable Long id) {

        AccommodationReview review = accommodationReviewService.findOne(id);

        if (review != null) {
            accommodationReviewService.remove(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
