package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationCardAdminDTO;
import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.dto.AccommodationCardHostDTO;
import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Address;
import com.tripster.project.model.Host;
import com.tripster.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccommodationDTOMapper {

    public static Accommodation fromDTOtoAccommodation(AccommodationDTO dto) {

        Accommodation accommodation = new Accommodation();
        Address address = new Address();

        accommodation.setId(dto.getId());
        accommodation.setName(dto.getName());
        accommodation.setOwner(dto.getOwner());
        accommodation.setDescription(dto.getDescription());
        accommodation.setAmenities(dto.getAmenities());
        accommodation.setMinCap(dto.getMinCap());
        accommodation.setMaxCap(dto.getMaxCap());
        accommodation.setCancelDuration(dto.getCancelDuration());
        accommodation.setType(dto.getType());
        accommodation.setStatus(dto.getStatus());
//        accommodation.setAutomaticReservation(dto.getsAutomaticReservation());

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
        dto.setOwner(accommodation.getOwner());
        dto.setDescription(accommodation.getDescription());
        dto.setAmenities(accommodation.getAmenities());
        dto.setMinCap(accommodation.getMinCap());
        dto.setMaxCap(accommodation.getMaxCap());
        dto.setCancelDuration(accommodation.getCancelDuration());
        dto.setType(accommodation.getType());
        dto.setStatus(accommodation.getStatus());
        //        dto.setPhoto(accommodation.getPhoto());
//        dto.setAutomaticReservation(accommodation.getsAutomaticReservation());

        dto.setCountry(address.getCountry());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setZipCode(address.getZipCode());

        return dto;
    }

    public static AccommodationCardAdminDTO fromAccommodationToAdminDTO(Accommodation accommodation) {
        Host owner = accommodation.getOwner();
        AccommodationCardAdminDTO dto = new AccommodationCardAdminDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
//        dto.setPhoto(accommodation.getPhoto());
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
        // TODO: Find the best way to get the Owner info
        dto.setOwnerName(owner.getName() + owner.getSurname());
//        dto.setOwnerEmail(owner.getUser().getEmail());
        dto.setStatus(accommodation.getStatus());
//        dto.setTimeStamp(accommodation.getTimeStamp());
//        dto.setShortDescription(accommodation.getShortDescription());
        dto.setType(accommodation.getType());
        // TODO: Find a way to resolve the Amenity issue
//        dto.setAmenities(accommodation.getAmenities());

        return dto;
    }

    public static AccommodationCardHostDTO fromAccommodationToHostDTO(Accommodation accommodation) {
        AccommodationCardHostDTO dto = new AccommodationCardHostDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
//        dto.setPhoto(accommodation.getPhoto());
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
//        dto.setFreeCancellation(accommodation.getFreeCancellation());
//        dto.setShortDescription(accommodation.getShortDescription());
        dto.setStatus(accommodation.getStatus());
        dto.setType(accommodation.getType());
        // TODO: Find a way to resolve the Amenity issue
//        dto.setAmenities(accommodation.getAmenities());
        return dto;
    }

    public static AccommodationCardGuestDTO fromAccommodationToGuestDTO(Accommodation accommodation) {
        AccommodationCardGuestDTO dto = new AccommodationCardGuestDTO();
        dto.setId(accommodation.getId());
        dto.setName(accommodation.getName());
//        dto.setPhoto(accommodation.getPhoto());
//        dto.setDistanceFromCenter(accommodation.setDistanceFromCenter());
//        dto.setFreeCancellation(accommodation.getFreeCancellation());
//        dto.setShortDescription(accommodation.getShortDescription());
        dto.setType(accommodation.getType());
        // TODO: Find a way to add reservation filters to this DTO
//        dto.setPrice();
//        dto.setDuration();
//        dto.setNumOfGuests();
        // TODO: Find a way to resolve the Amenity issue
//        dto.setAmenities(accommodation.getAmenities());

        return dto;
    }
}


