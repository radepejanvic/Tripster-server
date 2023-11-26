package com.tripster.project.dto;

import com.tripster.project.model.enums.ReviewStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

    private Long id;

    private int rate;

    private String comment;

    private ReviewStatus status;

    private Long reviewerId;

}
