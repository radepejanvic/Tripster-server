package com.tripster.project.controller;

import com.tripster.project.dto.*;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Host;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.model.enums.AccommodationType;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.AmenityService;
import com.tripster.project.service.CalendarService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AccommodationReviewService accommodationReviewService;


    // Admin: when he opens the page for accommodation approval
    @GetMapping(value = "/admin")
    public ResponseEntity<List<AccommodationCardAdminDTO>> getAccommodationsAdmin(@RequestParam(required = false) List<AccommodationStatus> statusList ) {
        List<Accommodation> accommodations = accommodationService.findByStatusForApproval(statusList);

        List<AccommodationCardAdminDTO> accommodationCards = accommodations.stream()
                .map(acc -> AccommodationDTOMapper.fromAccommodationToAdminDTO(acc, photoService.findPrimary(acc.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Host: when he opens the myAccommodations page
    @GetMapping(value = "/host/{hostId}")
    public ResponseEntity<List<AccommodationCardHostDTO>> getAccommodationsHost(@PathVariable Long hostId) {
        List<Accommodation> accommodations = accommodationService.findAllByOwnerId(hostId);

        List<AccommodationCardHostDTO> accommodationCards = accommodations.stream()
                .map(acc -> AccommodationDTOMapper.fromAccommodationToHostDTO(acc, photoService.findPrimary(acc.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    // Guest: when he searches for accommodations
    @GetMapping(value = "/guest")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodationsGuest(@RequestParam(required = false) String start, @RequestParam(required = false) String end, @RequestParam(required = false) Integer numOfGuests) {

        List<Accommodation> accommodations = accommodationService.findAll();
        List<AccommodationCardGuestDTO> accommodationCards = accommodations.stream()
                .map(acc -> AccommodationDTOMapper.fromAccommodationToGuestDTO(acc, photoService.findPrimary(acc.getId())))
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

    @GetMapping(value = "/guest/filters")
    public ResponseEntity<List<AccommodationCardGuestDTO> > filterAccommodations(@RequestParam(required = false) String city,
                                                               @RequestParam(required = false) String start,
                                                               @RequestParam(required = false) String end,
                                                               @RequestParam(required = false) Integer numOfGuests,
                                                               @RequestParam(required = false) Set<Long> amenities,
                                                               @RequestParam(required = false) Double minPrice,
                                                               @RequestParam(required = false) Double maxPrice,
                                                               @RequestParam(required = false) AccommodationType type) {

        List<Object[]> objects = accommodationService.filterAll(city, Long.parseLong(start), Long.parseLong(end), numOfGuests, amenities, minPrice, maxPrice, type);
        List<AccommodationCardGuestDTO> accommodationCardGuestDTOS = new ArrayList<>();
        for (Object[] obj: objects) {
            Object[] reviews = accommodationReviewService.countReviews((Long) obj[0]).get(0);
            Accommodation accommodation = accommodationService.findOne((Long) obj[0]);
            accommodationCardGuestDTOS.add(AccommodationDTOMapper.fromObjectToGuestDTO(accommodation,(Double)obj[2],(long)obj[1],numOfGuests,(Double) reviews[0],(Long) reviews[1],photoService.findPrimary((long)obj[0])));
        }
        return new ResponseEntity<>(accommodationCardGuestDTOS, HttpStatus.OK);
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
        accommodation.setTimeStamp(LocalDateTime.now());
        accommodation.setStatus(AccommodationStatus.NEW);

        accommodationService.save(accommodation);

        return new ResponseEntity<>(AccommodationDTOMapper.fromAccommodationToDTO(accommodation), HttpStatus.CREATED);
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
        accommodation.setAmenities(amenityService.findByIdIn(dto.getAmenities()));
        accommodation.setTimeStamp(LocalDateTime.now());
        accommodation.setStatus(AccommodationStatus.UPDATED);
        accommodationService.save(accommodation);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/price/{accommodationId}", consumes = "application/json")
    public ResponseEntity<Integer> addCalendar(@PathVariable Long accommodationId, @RequestBody List<PriceDTO> dtos) {

        Accommodation accommodation = accommodationService.findOne(accommodationId);
        accommodation.setCalendar(calendarService.getCalendar(dtos));
        accommodation.setTimeStamp(LocalDateTime.now());
        accommodation.setStatus(AccommodationStatus.UPDATED);
        accommodationService.save(accommodation);

        return new ResponseEntity<>(
                accommodation.getCalendar().size(), HttpStatus.OK);
    }

//    @PostMapping(value = "/calendar/{accommodationId}", consumes = "application/json")
//    public ResponseEntity<Integer> addCalendar(@PathVariable Long accommodationId, @RequestBody IntervalDTO) {
//
//        return new ResponseEntity<>(0
//                , HttpStatus.OK);
//    }

    @PutMapping(value = "/price/{accommodationId}", consumes = "application/json")
    public ResponseEntity<Integer> updateCalendar(@PathVariable Long accommodationId, @RequestBody List<PriceDTO> dtos) {

        Accommodation accommodation = accommodationService.findOne(accommodationId);
        accommodation.setTimeStamp(LocalDateTime.now());
        accommodation.setStatus(AccommodationStatus.UPDATED);

        int length = calendarService.updateCalendar(accommodationId, dtos);

        return new ResponseEntity<>(length, HttpStatus.OK);
    }

    @GetMapping(value = "calendar/{id}")
    public ResponseEntity<?> getCalendar(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        return new ResponseEntity<>(accommodation.getCalendar(), HttpStatus.OK);
    }

    @GetMapping(value = "pricelists/{id}")
    public ResponseEntity<List<PriceDTO>> getPricelists(@PathVariable Long id) {

        return new ResponseEntity<>(calendarService.getPricelists(id), HttpStatus.OK);
    }

    @PatchMapping(consumes = "application/json")
    public ResponseEntity<String> updateAccommodation(@RequestBody StatusDTO dto) {

        Accommodation accommodation = accommodationService.findOne(dto.getId());
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
