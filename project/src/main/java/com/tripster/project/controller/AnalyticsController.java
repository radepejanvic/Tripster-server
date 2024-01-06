package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationReviewReportDTO;
import com.tripster.project.dto.Analytics;
import com.tripster.project.dto.StatusAnalytics;
import com.tripster.project.mapper.ReviewReportDTOMapper;
import com.tripster.project.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

//    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/{id}/{year}")
    public ResponseEntity<List<Analytics>> getAnnualAnalytics(@PathVariable Long id, @PathVariable int year) {

        return new ResponseEntity<>(analyticsService.generateAnnualAnalytics(id, year), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/status/{id}/{start}/{end}")
    public ResponseEntity<StatusAnalytics> getStatusAnalytics(@PathVariable Long id, @PathVariable long start, @PathVariable long end) {

        return new ResponseEntity<>(analyticsService.generateStatusAnalytics(id, start, end), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/{id}/{start}/{end}")
    public ResponseEntity<List<Analytics>> getTotalAnalytics(@PathVariable Long id, @PathVariable long start, @PathVariable long end) {

        return new ResponseEntity<>(analyticsService.generateCustomPeriodAnalytics(id, start, end), HttpStatus.OK);
    }

}
