package com.tripster.project.controller;

import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.dto.FavoriteDTO;
import com.tripster.project.mapper.AccommodationDTOMapper;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.repository.PhotoRepository;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private IPersonService personService;

    @Autowired
    private PhotoService photoService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FavoriteDTO> addToFavorites(@RequestBody FavoriteDTO dto) {

        Guest guest = (Guest) personService.findById(dto.getGuestId());
        Accommodation accommodation = accommodationService.findOne(dto.getAccommodationId());

        Set<Accommodation> favorites = guest.getFavorites();
        favorites.add(accommodation);
        guest.setFavorites(favorites);

        personService.save(guest);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<AccommodationCardGuestDTO>> getAccommodation(@PathVariable Long id) {

        List<AccommodationCardGuestDTO> accommodationCards = ((Guest)personService.findById(id)).getFavorites().stream()
                .map(acc -> AccommodationDTOMapper.fromAccommodationToGuestDTO(acc, photoService.findPrimary(acc.getId())) )
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationCards, HttpStatus.OK);
    }

}
