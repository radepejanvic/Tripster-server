package com.tripster.project.controller;

import com.tripster.project.dto.ReviewDTO;
import com.tripster.project.mapper.ReviewDTOMapper;
import com.tripster.project.model.Notification;
import com.tripster.project.model.Person;
import com.tripster.project.model.Review;
import com.tripster.project.model.UserReview;
import com.tripster.project.model.enums.ReviewStatus;
import com.tripster.project.service.NotificationSendingService;
import com.tripster.project.service.ReviewService;
import com.tripster.project.service.UserReviewService;
import com.tripster.project.service.interfaces.IPersonService;
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
@RequestMapping(value = "api/users/reviews")
public class UserReviewController {

    @Autowired
    private UserReviewService userReviewService;
    @Autowired
    private UserService userService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private NotificationSendingService notificationSendingService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long userId) {
        List<UserReview> reviews = userReviewService.findAllByReviewedId(userId);

        Person reviewer;
        List<ReviewDTO> dtos = new ArrayList<>();
        for (Review review : reviews) {
            reviewer = guestService.findByUser(review.getReviewer());
            dtos.add(ReviewDTOMapper.fromReviewToDTO(review, reviewer.getName(), reviewer.getSurname()));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{hostId}/{guestId}")
    public ResponseEntity<Boolean> canReviewAccommodation(@PathVariable Long hostId, @PathVariable Long guestId) {
        return new ResponseEntity<>(reviewService.canReviewHost(hostId, guestId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDTO> saveAccommodation(@RequestBody ReviewDTO dto) {

        UserReview review = ReviewDTOMapper.fromDTOToUserReview(dto);
        review.setReviewer(userService.findOne(dto.getReviewerId()));
        review.setReviewedUser(userService.findOne(dto.getReviewedId()));
        review.setTimeStamp(LocalDateTime.now());
        review.setStatus(ReviewStatus.ACTIVE);
        userReviewService.save(review);

        notificationSendingService.send(new Notification(review));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteAccommodation(@PathVariable Long id) {

        UserReview review = userReviewService.findOne(id);

        if (review != null) {
            userReviewService.remove(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
