package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationCardAdminDTO;
import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.dto.AccommodationReviewDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.mapper.AccommodationReviewDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.Host;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private IUserService userService;

    @GetMapping(value = "/{accommodationId}")
    public ResponseEntity<List<AccommodationReviewDTO>> getReviews(@PathVariable Long accommodationId) {
        List<AccommodationReview> reviews = accommodationReviewService.findAll();

        List<AccommodationReviewDTO> dtos = reviews.stream()
                .map(AccommodationReviewDTOMapper::fromAccommodationReviewToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccommodationReviewDTO> saveAccommodation(@RequestBody AccommodationReviewDTO dto) {

        AccommodationReview review = AccommodationReviewDTOMapper.fromDTOToAccommodationReview(dto);
        review.setReviewer(userService.findOne(dto.getReviewerId()));
        accommodationReviewService.save(review);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }




}
