package com.tripster.project.controller;

import com.tripster.project.dto.*;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Host;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.AmenityService;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;
    @Autowired
    private AmenityService amenityService;

    @Qualifier("hostServiceImpl")
    @Autowired
    private IPersonService personService;


    // Admin: when he opens the page for accommodation approval
    @GetMapping(value = "/admin")
    public ResponseEntity<List<AccommodationCardAdminDTO>> getAccommodationsAdmin(@RequestParam(required = false) List<AccommodationStatus> statusList ) {
        List<Accommodation> accommodations = accommodationService.findByStatusIn(statusList);

        List<AccommodationCardAdminDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToAdminDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Host: when he opens the myAccommodations page
    @GetMapping(value = "/host/{hostId}")
    public ResponseEntity<List<AccommodationCardHostDTO>> getAccommodationsHost(@PathVariable Long hostId) {
        List<Accommodation> accommodations = accommodationService.findAllByOwnerId(hostId);

        List<AccommodationCardHostDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToHostDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Guest: when he searches for accommodations
    @GetMapping(value = "/guest")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodationsGuest(@RequestParam(required = false) String start, @RequestParam(required = false) String end, @RequestParam(required = false) Integer numOfGuests) {

        List<Accommodation> accommodations = accommodationService.findAll();
        List<AccommodationCardGuestDTO> accommodationCards = accommodations.stream()
                .map(AccommodationDTOMapper::fromAccommodationToGuestDTO)
                .collect(Collectors.toList());


//        for (Object[] o : accommodationService.findAllAvailableAccommodationsWithPrice(LocalDate.parse(start, formatter), LocalDate.parse(end, formatter), numOfGuests)) {
//            accommodationCards.add(AccommodationDTOMapper.fromAccommodationToGuestDTO((Accommodation) o[0], (double) o[1]));
//        }

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    @GetMapping(value = "/guest/filters")
    public ResponseEntity<List<Object[]>> filterAccommodations(@RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String start,
                                                               @RequestParam(required = false) String end,
                                                               @RequestParam(required = false) Integer numOfGuests,
                                                               @RequestParam(required = false) Set<Long> amenities) {

        List<Object[]> objects = accommodationService.filterAll(city, start, end, numOfGuests, amenities);

        return new ResponseEntity<>(objects, HttpStatus.OK);
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

        Accommodation accommodation = AccommodationDTOMapper.fromDTOtoAccommodation(dto);
        accommodation.setOwner((Host)personService.findById(dto.getOwnerId()));
        accommodation.setAmenities(amenityService.findByIdIn(dto.getAmenities()));

        accommodationService.generateCalendar(LocalDate.now(), accommodation);

        accommodationService.save(accommodation);

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
        accommodation.setOwner((Host)personService.findById(dto.getOwnerId()));
        accommodationService.save(accommodation);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/price/{accommodationId}", consumes = "application/json")
    public ResponseEntity<PriceDTO> updateAccommodation(@PathVariable Long accommodationId, @RequestBody PriceDTO dto) {

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json")
    public ResponseEntity<String> updateAccommodation(@RequestBody StatusDTO dto) {

        Accommodation accommodation = accommodationService.findOne(dto.getId());
        System.out.println(dto);
        accommodation.setStatus(AccommodationStatus.valueOf(dto.getStatus()));
        accommodationService.save(accommodation);

        return new ResponseEntity<>(dto.getStatus(), HttpStatus.OK);
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
