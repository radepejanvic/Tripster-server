package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Guest extends Person{

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Accommodation> favorites;

}
