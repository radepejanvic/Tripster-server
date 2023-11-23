package com.tripster.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Guest extends User{

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Accommodation> favourites;

}
