package com.tripster.project.service;

import com.tripster.project.model.AccommodationReviewReport;
import com.tripster.project.repository.AccommodationReviewReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationReviewReportServiceImpl {
    @Autowired
    private AccommodationReviewReportRepository accommodationReviewReportRepository;
    public AccommodationReviewReport findOne(Long id) { return accommodationReviewReportRepository.findById(id).orElseGet(null); }

    public List<AccommodationReviewReport> findAll() { return accommodationReviewReportRepository.findAll();  }
    public AccommodationReviewReport save(AccommodationReviewReport accommodationReviewReport) { return accommodationReviewReportRepository.save(accommodationReviewReport); }
    public void remove(Long id) { accommodationReviewReportRepository.deleteById(id); }
}
