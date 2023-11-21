package com.tripster.project.repository;

import com.tripster.project.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {
}
