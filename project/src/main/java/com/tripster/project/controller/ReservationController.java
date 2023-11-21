package com.tripster.project.controller;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Reservation;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/accommodations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getCourses() {
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reservation> getCourse(@PathVariable Long id) {

        Reservation reservation = reservationService.findOne(id);

        // course must exist
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Reservation> saveCourse(@RequestBody Reservation reservation) {
        reservation = reservationService.save(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Reservation> updateCourse(@RequestBody Reservation reservation) {
        Reservation res = reservationService.findOne(reservation.getId());

        if (res == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //Ovde treba videti apdejt kog atributa treba da se stavi
        //res.setName(reservation.getName());
        reservationService.save(res);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        Reservation reservation = reservationService.findOne(id);

        if (reservation != null) {
            reservationService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
