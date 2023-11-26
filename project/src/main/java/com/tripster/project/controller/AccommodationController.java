package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationCardAdminDTO;
import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.dto.AccommodationCardHostDTO;
import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    // Admin: when he opens the page for accommodation approval
    @GetMapping(value = "/admin")
    public ResponseEntity<List<AccommodationCardAdminDTO>> getAccommodationsAdmin() {
        List<Accommodation> accommodations = accommodationService.findAll();

        List<AccommodationCardAdminDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToAdminDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Host: when he opens the myAccommodations page
    @GetMapping(value = "/host")
    public ResponseEntity<List<AccommodationCardHostDTO>> getAccommodationsHost() {
        List<Accommodation> accommodations = accommodationService.findAll();

        List<AccommodationCardHostDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToHostDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Guest: when he searches for accommodations
    @GetMapping(value = "/guest")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodationsGuest() {
        List<Accommodation> accommodations = accommodationService.findAll();

        List<AccommodationCardGuestDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToGuestDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Host: when he opens the form for update
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        if (accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(AccommodationDTOMapper.fromAccommodationToDTO(accommodation), HttpStatus.OK);
    }

    // Host: when he opens the form for registering new accommodation
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccommodationDTO> saveAccommodation(@RequestBody AccommodationDTO dto) {
        accommodationService.save(AccommodationDTOMapper.fromDTOtoAccommodation(dto));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // Host: when he opens the form for update
    @PutMapping(consumes = "application/json")
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO dto) {
        Accommodation accommodation = accommodationService.findOne(dto.getId());

        if (accommodation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accommodation = AccommodationDTOMapper.fromDTOtoAccommodation(dto);
        accommodationService.save(accommodation);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Host:
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        if (accommodation != null) {
            accommodationService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
