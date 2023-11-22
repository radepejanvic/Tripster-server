package com.tripster.project.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccommodationReview extends Review{
    // TODO: Add ManyToOne relationship with Accommodation
}
