package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.mapper.UserReportDTOMapper;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.model.User;
import com.tripster.project.service.AccommodationReviewReportServiceImpl;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/accommodations/reviews/reports")
public class AccommodationReviewReportController {

    @Autowired
    private AccommodationReviewReportServiceImpl accommodationReviewReportService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccommodationReviewService accommodationReviewService;

    @GetMapping
    public ResponseEntity<List<AccommodationReviewReportDTO>> getAll() {

        List<AccommodationReviewReportDTO> reports = accommodationReviewReportService.findAll().stream()
                .map(ReviewReportDTOMapper::fromAccommodationReviewReportToDTO)
                .toList();

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccommodationReviewReportDTO> getOne(@PathVariable Long id) {

        AccommodationReviewReport report = accommodationReviewReportService.findOne(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ReviewReportDTOMapper.fromAccommodationReviewReportToDTO(report), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccommodationReviewReportDTO> reportReview(@RequestBody AccommodationReviewReportDTO dto) {

        User reporter = userService.findOne(dto.getReporterId());
        AccommodationReview review = accommodationReviewService.findOne(dto.getReviewId());

        if (reporter == null || review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AccommodationReviewReport report = ReviewReportDTOMapper.fromDTOToAccommodationReviewReport(dto);
        report.setReporter(reporter);
        report.setReview(review);
        accommodationReviewReportService.save(report);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        AccommodationReviewReport report = accommodationReviewReportService.findOne(id);

        if ( report != null ) {
            accommodationReviewReportService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
