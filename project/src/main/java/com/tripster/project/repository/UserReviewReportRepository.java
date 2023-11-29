package com.tripster.project.repository;

import com.tripster.project.model.UserReviewReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewReportRepository extends JpaRepository<UserReviewReport, Long> {
}
