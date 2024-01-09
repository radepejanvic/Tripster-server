package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SQLDelete(sql = "update accommodation_review_report "
        + "set status = 'DELETED' "
        + "where id = ?")
@Where(clause = "status != 'DELETED'")
public class AccommodationReviewReport extends Report{

    @OneToOne(fetch = FetchType.EAGER)
    private AccommodationReview review;

}
