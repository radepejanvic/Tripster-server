package com.tripster.project.dto;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccommodationReviewDTO {

    private Long id;

    private int rate;

    private String comment;

    private ReviewStatus status;

    private Long reviewerId;

}
