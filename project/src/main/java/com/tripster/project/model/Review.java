package com.tripster.project.model;

import com.tripster.project.model.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Review{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int rate;

    private String comment;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private User reviewer;
}
