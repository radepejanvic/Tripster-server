package com.tripster.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "update accommodation_review "
        + "set status = 'DELETED' "
        + "where id = ?")
@Where(clause = "status != 'DELETED'")
public class AccommodationReview extends Review{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

}
