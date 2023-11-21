package com.tripster.project.controller;

import com.tripster.project.model.Accommodation;
import com.tripster.project.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

}
