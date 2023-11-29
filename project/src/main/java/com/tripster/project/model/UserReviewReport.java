package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserReviewReport extends Report{

    @OneToOne(fetch = FetchType.EAGER)
    private UserReview review;

}
