package com.tripster.project.controller;

import com.tripster.project.dto.ReservationDTO;
import com.tripster.project.dto.ReservationGuestDTO;
import com.tripster.project.mapper.ReservationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Notification;
import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import com.tripster.project.service.*;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private PhotoService photoService;
    @Autowired
    private CalendarService calendarService;


    @Autowired
    private NotificationSendingService notificationSendingService;


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
    public ResponseEntity<List<ReservationGuestDTO>> getAllForGuest(@PathVariable Long id) {
        List<Reservation> reservations = reservationService.getAllForGuest(id);
        List<ReservationGuestDTO> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromGuestReservationToDTO(res,photoService.findPrimary(res.getAccommodation().getId())));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value = "/guest/filter/{id}")
    public ResponseEntity<List<ReservationGuestDTO> > filterGuestReseravtion(@PathVariable Long id,
                                                                                 @RequestParam(required = false) String name,
                                                                                 @RequestParam(required = false) String start,
                                                                                 @RequestParam(required = false) String end,
                                                                                 @RequestParam(required = false) List<ReservationStatus> statusList) {
        List<Reservation> reservations = reservationService.findByGuestFilter(id,name,Long.parseLong(start),Long.parseLong(end),statusList);
        List<ReservationGuestDTO> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromGuestReservationToDTO(res,photoService.findPrimary(res.getAccommodation().getId())));
        }
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @GetMapping(value = "/host/filter/{id}")
    public ResponseEntity<List<ReservationGuestDTO> > filterHostReservation(@PathVariable Long id,
                                                                           @RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) String start,
                                                                           @RequestParam(required = false) String end,
                                                                           @RequestParam(required = false) List<ReservationStatus> statusList) {
        List<Reservation> reservations = reservationService.findByHostFilter(id,name,Long.parseLong(start),Long.parseLong(end),statusList);
        List<ReservationGuestDTO> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromReservationToHostDTO(res,photoService.findPrimary(res.getAccommodation().getId()), reservationService.calculateNumberOfCancelled(res.getGuest().getId())));
        }
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @GetMapping(value = "/host/{id}")
    public ResponseEntity<List<ReservationGuestDTO>> getAllForHost(@PathVariable Long id) {
        List<Reservation> reservations = reservationService.getAllForHost(id);
        //Ovde gore treba da ide get all for host                     ^
        List<ReservationGuestDTO> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            dtos.add(ReservationDTOMapper.fromReservationToHostDTO(res,photoService.findPrimary(res.getAccommodation().getId()),reservationService.calculateNumberOfCancelled(res.getGuest().getId())));
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
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReservationDTO> addNew(@RequestBody ReservationDTO reservationDTO) {

        Accommodation accommodation = accommodationService.findOne(reservationDTO.getAccommodationId());
        Guest guest = (Guest) guestService.findById(reservationDTO.getGuestId());

        if (guest == null || accommodation == null) {
            return new ResponseEntity<>(reservationDTO, HttpStatus.NOT_FOUND);
        }

        Reservation reservation = ReservationDTOMapper.fromDTOtoReservation(reservationDTO);
        reservation.setAccommodation(accommodation);
        reservation.setGuest(guest);
        if (accommodation.isAutomaticReservation()) {
            reservation.setStatus(ReservationStatus.ACCEPTED);
            calendarService.reserveDays(accommodation.getId(), reservation.getStart(), reservation.getEnd());
        }
        else reservation.setStatus(ReservationStatus.PENDING);
        reservationService.save(reservation);

        notificationSendingService.send(new Notification(reservation));

        return new ResponseEntity<>(reservationDTO, HttpStatus.CREATED);
    }
    @GetMapping(value = "/dateRangeAccommodation")
    public ResponseEntity<List<ReservationDTO>> dateRangeAccommodation(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam Long accId){
        //defaultni parametri se ipak trebaju staviti na frontu
        List<Reservation> reservations = reservationService.getAllInDateRangeForAccommodation(startDate, endDate, accId);
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

    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/accept/{id}")
    public ResponseEntity<Void> acceptReservation(@PathVariable Long id) {

        Reservation reservation = reservationService.findOne(id);
        if (reservation == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        for (Reservation res : reservationService.getAllInDateRangeForAccommodation(reservation.getStart(), reservation.getEnd(), reservation.getId())) {
            reservationService.remove(res.getId());
        }

        reservation.setStatus(ReservationStatus.ACCEPTED);
        reservationService.save(reservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/reject/{id}")
    public ResponseEntity<Void> rejectReservation(@PathVariable Long id) {

        Reservation reservation = reservationService.findOne(id);
        if (reservation == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        reservation.setStatus(ReservationStatus.REJECTED);
        reservationService.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Reservation reservation = reservationService.findOne(id);

        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (reservation.getStatus() != ReservationStatus.ACCEPTED) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        long cancelDuration = accommodationService.findOne(reservation.getId()).getCancelDuration();
        long daysToReservation = reservation.getStart().toEpochDay() - LocalDate.now().toEpochDay();
        if (cancelDuration > daysToReservation) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservationService.remove(id);
        calendarService.unreserveDays(reservation.getAccommodation().getId(), reservation.getStart(), reservation.getEnd());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



