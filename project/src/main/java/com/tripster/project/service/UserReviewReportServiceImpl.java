package com.tripster.project.service;

import com.tripster.project.model.UserReviewReport;
import com.tripster.project.repository.UserReviewReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewReportServiceImpl {
    @Autowired
    private UserReviewReportRepository userReviewReportRepository;
    public UserReviewReport findOne(Long id) { return userReviewReportRepository.findById(id).orElseGet(null); }

    public List<UserReviewReport> findAll() { return userReviewReportRepository.findAll();  }
    public UserReviewReport save(UserReviewReport userReviewReport) { return userReviewReportRepository.save(userReviewReport); }
    public void remove(Long id) { userReviewReportRepository.deleteById(id); }

}
