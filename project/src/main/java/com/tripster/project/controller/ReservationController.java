package com.tripster.project.controller;

import com.tripster.project.dto.ReservationDTO;
import com.tripster.project.mapper.ReservationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.GuestServiceImpl;
import com.tripster.project.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private GuestServiceImpl guestService;
    @Autowired
    private AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll() {
        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromReservationToDTO(res));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value = "/guest/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllForGuest(@PathVariable Long id) {
        List<Reservation> reservations = reservationService.getAllForGuest(id);
        List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromReservationToDTO(res));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value = "/host/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllForHost(@PathVariable Long id) {
        List<Reservation> reservations = reservationService.getAllForHost(id);
        //Ovde gore treba da ide get all for host                     ^
        List<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromReservationToDTO(res));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> get(@PathVariable Long id) {

        ReservationDTO reservationDTO = ReservationDTOMapper.fromReservationToDTO(reservationService.findOne(id));

        if (reservationDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReservationDTO> addNew(@RequestBody ReservationDTO reservationDTO) {

        Reservation res = ReservationDTOMapper.fromDTOtoReservation(reservationDTO);
        Accommodation acc = accommodationService.findOne(reservationDTO.getAccmId());
        if (acc == null) {
            return new ResponseEntity<>(reservationDTO, HttpStatus.BAD_REQUEST);
        }
        Guest guest = (Guest) guestService.findById(reservationDTO.getGuestUserId());
        if (guest == null) {
            return new ResponseEntity<>(reservationDTO, HttpStatus.BAD_REQUEST);
        }
        res.setAccommodation(acc);
        res.setGuest(guest);
        reservationService.save(res);
        return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
    }
    @GetMapping(value = "/dateRange")
    public ResponseEntity<List<ReservationDTO>> filterSearch(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        //List<ReservationDTO> dummyResult = new ArrayList<ReservationDTO>();
        //Dummy funkcija za sada koja treba da se napravi kasnije
        //U njoj treba proveriti filtere da li dobro salje/hvata parametre
        //staviti ovde da stoje defaultni parametri filter pretrage
        //defaultni se ipak trebaju staviti na frontu
        List<Reservation> reservations = reservationService.getAllInDateRange(startDate, endDate);
        List<ReservationDTO> dtos = reservations.stream()
                .map(ReservationDTOMapper::fromReservationToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping(value = "/dateRange")
    public ResponseEntity<Void> setStatus(@RequestParam ReservationStatus status, @RequestParam Long reservationId) {

        Reservation reservation = reservationService.findOne(reservationId);
        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservation.setStatus(status);
        reservationService.save(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
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



