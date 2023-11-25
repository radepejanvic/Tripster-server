package com.tripster.project.controller;

import com.tripster.project.dtos.ReservationDTO;
import com.tripster.project.mapper.ReservationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Reservation;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> get() {
        //Ovde ispraviti
        List<Reservation> reservations = reservationService.findAll();
        //List<ReservationDTO> reservationDTOS = ReservationDTOMapper.fromReservationToDTO(reservations.stream());
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> get(@PathVariable Long id) {

        ReservationDTO reservationDTO = ReservationDTOMapper.fromReservationToDTO(reservationService.findOne(id));

        // course must exist
        if (reservationDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservationDTO) {
        reservationDTO = reservationService.save(reservationDTO);
        return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO resDTO = ReservationDTOMapper.fromReservationToDTO(reservationService.findOne(reservationDTO.getId()));

        if (resDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservationService.save(resDTO);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        ReservationDTO reservation = ReservationDTOMapper.fromReservationToDTO(reservationService.findOne(id));

        if (reservation != null) {
            reservationService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



