package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FavoritesService {

    @Autowired
    @Qualifier("guestServiceImpl")
    private IPersonService guestService;

    @Autowired
    private AccommodationService accommodationService;

    public void toggleFavorites(Guest guest, Accommodation accommodation) {
        Set<Accommodation> favorites = guest.getFavorites() != null ? guest.getFavorites() : new HashSet<>();

        if (favorites.contains(accommodation)) {
            favorites.remove(accommodation);
        } else {
            favorites.add(accommodation);
        }

        guest.setFavorites(favorites);
        guestService.save(guest);
    }

}
