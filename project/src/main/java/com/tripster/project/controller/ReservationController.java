package com.tripster.project.controller;

import com.tripster.project.dto.ReservationDTO;
import com.tripster.project.mapper.ReservationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Reservation;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.GuestServiceImpl;
import com.tripster.project.service.ReservationServiceImpl;
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
        List<Reservation> reservations = reservationService.getAllForGuest(id);
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
    @GetMapping(value = "/filter")
    public ResponseEntity<List<ReservationDTO>> filterSearch(@RequestParam String startDateString, @RequestParam String endDateString, @RequestParam int numberOfGuests){
        List<ReservationDTO> dummyResult = new ArrayList<ReservationDTO>();
        //Dummy funkcija za sada koja treba da se napravi kasnije
        //U njoj treba proveriti filtere da li dobro salje/hvata parametre
        return new ResponseEntity<>(dummyResult, HttpStatus.OK);
    }

    @PutMapping(value = "/filter")
    public ResponseEntity<Void> setStatus(@RequestParam String status, @RequestParam Long reservationId) {
        //Ovde treba da se postavi status za odredjenu rezervaciju
        //Brisanje apdejt itd...
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



