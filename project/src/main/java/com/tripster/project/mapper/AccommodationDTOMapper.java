package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationCardAdminDTO;
import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.dto.AccommodationCardHostDTO;
import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.model.*;
import com.tripster.project.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Component
public class AccommodationDTOMapper {

    private static Set<Long> fromAmenitiesToLongs(Set<Amenity> amenities) {

        Set<Long> ids = new HashSet<>();

        for (Amenity a : amenities) {
            ids.add(a.getId());
        }

        return ids;
    }


    public static Accommodation fromDTOtoAccommodation(AccommodationDTO dto) {

        Accommodation accommodation = new Accommodation();
        Address address = new Address();

        accommodation.setId(dto.getId());
        accommodation.setName(dto.getName());
//        accommodation.setOwner(dto.getOwner());
        accommodation.setShortDescription(dto.getShortDescription());
        accommodation.setDescription(dto.getDescription());
//        accommodation.setAmenities(dto.getAmenities());
        accommodation.setMinCap(dto.getMinCap());
        accommodation.setMaxCap(dto.getMaxCap());
        accommodation.setCancelDuration(dto.getCancelDuration());
        accommodation.setType(dto.getType());
        accommodation.setAutomaticReservation(dto.isAutomaticReservation());
        accommodation.setStatus(dto.getStatus());

        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setZipCode(dto.getZipCode());

        accommodation.setAddress(address);

        return accommodation;
    }

    public static AccommodationDTO fromAccommodationToDTO(Accommodation accommodation) {

        AccommodationDTO dto = new AccommodationDTO();
        Address address = accommodation.getAddress();

        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
        dto.setOwnerId(accommodation.getOwner().getId());
        dto.setShortDescription(accommodation.getShortDescription());
        dto.setDescription(accommodation.getDescription());
        dto.setMinCap(accommodation.getMinCap());
        dto.setMaxCap(accommodation.getMaxCap());
        dto.setCancelDuration(accommodation.getCancelDuration());
        dto.setType(accommodation.getType());
        dto.setAutomaticReservation(accommodation.isAutomaticReservation());
        dto.setStatus(accommodation.getStatus());
        dto.setAmenities(fromAmenitiesToLongs(accommodation.getAmenities()));
        //        dto.setPhoto(accommodation.getPhoto());
//
        dto.setCountry(address.getCountry());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setZipCode(address.getZipCode());

        return dto;
    }

    public static AccommodationCardAdminDTO fromAccommodationToAdminDTO(Accommodation accommodation, byte[] photo) {
        Host owner = accommodation.getOwner();
        AccommodationCardAdminDTO dto = new AccommodationCardAdminDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
        dto.setPhoto(photo);
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
        // TODO: Find the best way to get the Owner info
//        dto.setOwnerName(owner.getName() + owner.getSurname());
//        dto.setOwnerEmail(owner.getUser().getEmail());
        dto.setAddress(accommodation.getAddress().toString());
//        dto.setPhoto(accommodation.getPhoto());
        dto.setStatus(accommodation.getStatus());
        dto.setShortDescription(accommodation.getShortDescription());
        dto.setTimeStamp(accommodation.getTimeStamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")).toString());
        return dto;
    }

    public static AccommodationCardHostDTO fromAccommodationToHostDTO(Accommodation accommodation, byte[] photo) {
        AccommodationCardHostDTO dto = new AccommodationCardHostDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
        dto.setPhoto(photo);
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
//        dto.setFreeCancellation(accommodation.getFreeCancellation());
//        dto.setShortDescription(accommodation.getShortDescription());
        dto.setStatus(accommodation.getStatus());
        dto.setType(accommodation.getType());
        // TODO: Find a way to resolve the Amenity issue
//        dto.setAmenities(accommodation.getAmenities());
        return dto;
    }

    public static AccommodationCardGuestDTO fromAccommodationToGuestDTO(Accommodation accommodation, byte[] photo) {
        AccommodationCardGuestDTO dto = new AccommodationCardGuestDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
        dto.setPhoto(photo);
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
//        dto.setFreeCancellation(accommodation.getFreeCancellation());
//        dto.setShortDescription(accommodation.getShortDescription());
//        dto.setType(accommodation.getType());
        // TODO: Find a way to add reservation filters to this DTO
//        dto.setPrice(price);
//        dto.setDuration();
//        dto.setNumOfGuests();
        // TODO: Find a way to resolve the Amenity issue
//        dto.setAmenities(accommodation.getAmenities());

        return dto;
    }
    public static AccommodationCardGuestDTO fromObjectToGuestDTO(Accommodation accommodation, double price, long count,Integer numOfGuests,float rating,int numOfReviews) {
        AccommodationCardGuestDTO dto = new AccommodationCardGuestDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());

        // TODO: Added picuters

        dto.setAddress(accommodation.getAddress().toString());
        dto.setShortDescription(accommodation.getShortDescription());
        dto.setPrice(price);
        dto.setPricePerNight(price/count);
        dto.setDuration(count);
        dto.setNumOfGuests(numOfGuests!=null ? numOfGuests: accommodation.getMaxCap());
        dto.setRating(rating);
        dto.setNumOfReviews(numOfReviews);
        return dto;
    }
}


