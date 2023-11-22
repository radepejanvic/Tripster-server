package com.tripster.project.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Report {
    @Id
    private Long id;
    private String reason;
    // TODO: Add ManyToOne relationship with User
}
