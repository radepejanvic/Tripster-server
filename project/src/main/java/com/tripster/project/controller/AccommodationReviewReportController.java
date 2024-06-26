package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.mapper.UserReportDTOMapper;
import com.tripster.project.model.AccommodationReview;
import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.ReportStatus;
import com.tripster.project.service.AccommodationReviewReportServiceImpl;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.interfaces.PhotoService;
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

    @Autowired
    private PhotoService photoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccommodationReviewReportDTO>> getAll() {

        List<AccommodationReviewReport> reports = accommodationReviewReportService.findAll();
        List<AccommodationReviewReportDTO> accommodationReviewReportDTOS = new ArrayList<>();
        for(AccommodationReviewReport report : reports){
            accommodationReviewReportDTOS.add(ReviewReportDTOMapper.fromAccommodationReviewReportToDTO(report, photoService.findPrimary(report.getReview().getAccommodation().getId())));
        }

        return new ResponseEntity<>(accommodationReviewReportDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccommodationReviewReportDTO> getOne(@PathVariable Long id) {

        AccommodationReviewReport report = accommodationReviewReportService.findOne(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ReviewReportDTOMapper.fromAccommodationReviewReportToDTO(report,photoService.findPrimary(1L)), HttpStatus.OK);
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
        report.setStatus(ReportStatus.ACTIVE);
        accommodationReviewReportService.save(report);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{reportId}")
    public ResponseEntity<Long> approveReport(@PathVariable Long reportId) {

        AccommodationReviewReport report = accommodationReviewReportService.findOne(reportId);
        AccommodationReview review = accommodationReviewService.findOne(report.getReview().getId());

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accommodationReviewReportService.remove(report.getId());
        accommodationReviewService.remove(review.getId());

        return new ResponseEntity<>(review.getId(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
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
