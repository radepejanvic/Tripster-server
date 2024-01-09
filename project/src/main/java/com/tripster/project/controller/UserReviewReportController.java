package com.tripster.project.controller;

import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.dto.UserReviewReportDTO;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.mapper.UserReportDTOMapper;
import com.tripster.project.model.User;
import com.tripster.project.model.UserReview;
import com.tripster.project.model.UserReviewReport;
import com.tripster.project.model.enums.ReportStatus;
import com.tripster.project.service.UserReviewReportServiceImpl;
import com.tripster.project.service.UserReviewService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users/reviews/reports")
public class UserReviewReportController {

    @Autowired
    private UserReviewReportServiceImpl userReviewReportService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReviewService userReviewService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserReviewReportDTO>> getAll() {

        List<UserReviewReportDTO> reports = userReviewReportService.findAll().stream()
                .map(ReviewReportDTOMapper::fromUserReviewReportToDTO)
                .toList();

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserReviewReportDTO> getOne(@PathVariable Long id) {

        UserReviewReport report = userReviewReportService.findOne(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ReviewReportDTOMapper.fromUserReviewReportToDTO(report), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserReviewReportDTO> reportReview(@RequestBody UserReviewReportDTO dto) {

        User reporter = userService.findOne(dto.getReporterId());
        UserReview review = userReviewService.findOne(dto.getReviewId());

        if (reporter == null || review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserReviewReport report = ReviewReportDTOMapper.fromDTOToUserReviewReport(dto);
        report.setReporter(reporter);
        report.setReview(review);
        report.setStatus(ReportStatus.ACTIVE);
        userReviewReportService.save(report);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        UserReviewReport report = userReviewReportService.findOne(id);

        if (report != null) {
            userReviewReportService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
