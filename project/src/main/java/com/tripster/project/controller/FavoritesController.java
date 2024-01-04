package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.FavoritesService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodation(@PathVariable Long id) {

        List<AccommodationCardGuestDTO> accommodationCards = ((Guest) guestService.findById(id)).getFavorites().stream()
                .map(acc -> AccommodationDTOMapper.fromAccommodationToGuestDTO(acc, photoService.findPrimary(acc.getId())) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

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
