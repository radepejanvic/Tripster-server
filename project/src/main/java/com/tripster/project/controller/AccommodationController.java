package com.tripster.project.controller;

import com.tripster.project.model.Accommodation;
import com.tripster.project.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<List<Accommodation>> getCourses() {
        List<Accommodation> accommodations = accommodationService.findAll();
        return new ResponseEntity<>(accommodations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Accommodation> getCourse(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        // course must exist
        if (accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accommodation, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Accommodation> saveCourse(@RequestBody Accommodation accommodation) {
        accommodation = accommodationService.save(accommodation);
        return new ResponseEntity<>(accommodation, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Accommodation> updateCourse(@RequestBody Accommodation accommodation) {
        Accommodation acc = accommodationService.findOne(accommodation.getId());

        if (acc == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        acc.setName(accommodation.getName());
        accommodationService.save(acc);
        return new ResponseEntity<>(accommodation, HttpStatus.OK);
    }

}
