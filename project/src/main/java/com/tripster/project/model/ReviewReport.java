package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;

@Entity
public class ReviewReport extends Report{
    // TODO: Add OneToOne relationship with Review

//    @OneToOne(fetch = FetchType.EAGER)
//    private Review review;
}
