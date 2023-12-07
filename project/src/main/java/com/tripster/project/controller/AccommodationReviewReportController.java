package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.model.User;
import com.tripster.project.service.AccommodationReviewReportServiceImpl;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/reports/accommodationReviews")
public class AccommodationReviewReportController {
    @Autowired
    private AccommodationReviewReportServiceImpl accommodationReviewReportService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccommodationReviewService accommodationReviewService;


    @GetMapping
    public ResponseEntity<List<AccommodationReviewReportDTO>> getAll() {
        List<AccommodationReviewReport> reports = accommodationReviewReportService.findAll();
        List<AccommodationReviewReportDTO> dtos = new ArrayList<AccommodationReviewReportDTO>();
        for (AccommodationReviewReport rep : reports) {
            dtos.add(ReviewReportDTOMapper.fromAccommodationReviewReportToDTO(rep));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccommodationReviewReportDTO> getOne(@PathVariable Long id) {
        AccommodationReviewReport report = accommodationReviewReportService.findOne(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccommodationReviewReportDTO dto = ReviewReportDTOMapper.fromAccommodationReviewReportToDTO(report);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccommodationReviewReportDTO> addNew(@RequestBody AccommodationReviewReportDTO dto) {
        AccommodationReviewReport report = ReviewReportDTOMapper.fromDTOToAccommodationReviewReport(dto);
        User reporter = userService.findOne(dto.getReporterId());
        if (reporter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccommodationReview review = accommodationReviewService.findOne(dto.getReviewId());
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        report.setReporter(reporter);
        report.setReview(review);
        accommodationReviewReportService.save(report);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        AccommodationReviewReport report = accommodationReviewReportService.findOne(id);
        if ( report == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accommodationReviewReportService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
