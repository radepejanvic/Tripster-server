package com.tripster.project.controller;

import com.tripster.project.dto.UserReviewReportDTO;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.model.User;
import com.tripster.project.model.UserReview;
import com.tripster.project.model.UserReviewReport;
import com.tripster.project.service.UserReviewReportServiceImpl;
import com.tripster.project.service.UserReviewService;
import com.tripster.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/reports/userReviews")
public class UserReviewReportController {
    @Autowired
    private UserReviewReportServiceImpl userReviewReportService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserReviewService userReviewService;


    @GetMapping
    public ResponseEntity<List<UserReviewReportDTO>> getAll() {
        List<UserReviewReport> reports = userReviewReportService.findAll();
        List<UserReviewReportDTO> dtos = new ArrayList<UserReviewReportDTO>();
        for (UserReviewReport rep : reports) {
            dtos.add(ReviewReportDTOMapper.fromUserReviewReportToDTO(rep));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserReviewReportDTO> getOne(@PathVariable Long id) {
        UserReviewReport report = userReviewReportService.findOne(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserReviewReportDTO dto = ReviewReportDTOMapper.fromUserReviewReportToDTO(report);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserReviewReportDTO> addNew(@RequestBody UserReviewReportDTO userReviewReportDTO) {
        UserReviewReport report = ReviewReportDTOMapper.fromDTOToUserReviewReport(userReviewReportDTO);
        User reporter = userService.findOne(userReviewReportDTO.getReporterId());
        if (reporter == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserReview review = userReviewService.findOne(userReviewReportDTO.getReviewId());
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        report.setReporter(reporter);
        report.setReview(review);
        userReviewReportService.save(report);
        return new ResponseEntity<>(userReviewReportDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserReviewReport report = userReviewReportService.findOne(id);
        if ( report == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userReviewReportService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
