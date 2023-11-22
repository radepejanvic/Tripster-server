package com.tripster.project.model;

import com.tripster.project.model.enums.ReviewStatus;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Review {
    @Id
    private Long id;
    private int rate;
    private String comment;
    private ReviewStatus status;
    // TODO: Add ManyToOne relationship with User
}
