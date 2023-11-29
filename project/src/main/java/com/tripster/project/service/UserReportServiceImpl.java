package com.tripster.project.service;

import com.tripster.project.model.UserReport;
import com.tripster.project.repository.UserReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReportServiceImpl {
    @Autowired
    private UserReportRepository userReportRepository;
    public UserReport findOne(Long id) {
        return userReportRepository.findById(id).orElseGet(null);
    }
    public List<UserReport> findAll() { return userReportRepository.findAll(); }
    public UserReport save(UserReport userReport) {
        return userReportRepository.save(userReport);
    }
    public void remove(Long id) { userReportRepository.deleteById(id); }
}
