package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.service.AccommodationReviewService;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.FavoritesService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/accommodations/favorites")
public class FavoritesController {

    @Autowired
    private AccommodationService accommodationService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private AccommodationReviewService accommodationReviewService;

    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(value = "/{guestId}/{accommodationId}")
    public ResponseEntity<Long> toggleFavorite(@PathVariable Long guestId, @PathVariable Long accommodationId) {

        Guest guest = (Guest) guestService.findById(guestId);
        Accommodation accommodation = accommodationService.findOne(accommodationId);

        if(guest == null || accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        favoritesService.toggleFavorites(guest, accommodation);

        return new ResponseEntity<>(accommodationId, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodation(@PathVariable Long id) {



        Set<Accommodation> objects =  ((Guest) guestService.findById(id)).getFavorites();
        List<AccommodationCardGuestDTO> accommodationCardGuestDTOS = new ArrayList<>();
        for (Accommodation obj: objects) {
            Object[] reviews = accommodationReviewService.countReviews(obj.getId()).get(0);
            Accommodation accommodation = accommodationService.findOne(obj.getId());
            accommodationCardGuestDTOS.add(AccommodationDTOMapper.fromObjectToGuestDTO(accommodation,0,0,accommodation.getMaxCap(),(Double) reviews[0],(Long) reviews[1],photoService.findPrimary(accommodation.getId())));
        }
        return new ResponseEntity<>(accommodationCardGuestDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value = "/{guestId}/{accommodationId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long guestId, @PathVariable Long accommodationId) {

        Guest guest = (Guest) guestService.findById(guestId);
        Accommodation accommodation = accommodationService.findOne(accommodationId);

        if(guest == null || accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(favoritesService.isFavorite(guest, accommodation), HttpStatus.OK);
    }

}
