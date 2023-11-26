package com.tripster.project.mapper;

import com.tripster.project.dtos.ReservationDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationDTOMapper {
    /*private static ModelMapper modelMapper;
    @Autowired
    public ReservationDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }*/

    public static Reservation fromDTOtoReservation(ReservationDTO dto) {

        Reservation reservation = new Reservation();
        //Reservation info
        reservation.setId(dto.getId());
        reservation.setStart(dto.getStart());
        reservation.setEnd(dto.getEnd());
        reservation.setDuration(dto.getDuration());
        reservation.setGuestsNo(dto.getGuestsNo());
        reservation.setPrice(dto.getPrice());
        reservation.setStatus(dto.getStatus());

        //Guest info
        Guest guest = new Guest();
        guest.setId(dto.getGuestUserId());
        reservation.setGuest(guest);

        //Accommodation info
        Accommodation accommodation = new Accommodation();
        accommodation.setId(dto.getAccmId());
        reservation.setAccommodation(accommodation);

        return reservation;
    }
    public static ReservationDTO fromReservationToDTO(Reservation res) {
        //return modelMapper.map(res, ReservationDTO.class);
        if (res == null) {
            return null;
        }
        //Reservation info
        ReservationDTO dto = new ReservationDTO();
        dto.setId(res.getId());
        dto.setStart(res.getStart());
        dto.setEnd(res.getEnd());
        dto.setDuration(res.getDuration());
        dto.setGuestsNo(res.getGuestsNo());
        dto.setPrice(res.getPrice());
        dto.setStatus(res.getStatus());

        //User info
        dto.setGuestUserId(res.getGuest().getId());
        dto.setGuestUserEmail(res.getGuest().getEmail());
        dto.setGuestUserType(res.getGuest().getUserType());
        dto.setGuestUserStatus(res.getGuest().getStatus());
        dto.setGuestUserPhone(res.getGuest().getPerson().getPhone());

        //Accommodation info
        dto.setAccmId(res.getAccommodation().getId());
        Accommodation acc = res.getAccommodation();
        dto.setAccmName(acc.getName());
        //Owner info
        dto.setAccmOwnerEmail(res.getAccommodation().getOwner().getEmail());
        dto.setAccmOwnerName(res.getAccommodation().getOwner().getPerson().getName());
        dto.setAccmOwnerSurname(res.getAccommodation().getOwner().getPerson().getSurname());
        dto.setAccmOwnerPhone(res.getAccommodation().getOwner().getPerson().getPhone());
        //Accommodation details info
        dto.setAmenities(res.getAccommodation().getAmenities());
        dto.setPhoto(res.getAccommodation().getPhoto());
        dto.setMaxCap(res.getAccommodation().getMaxCap());
        dto.setMinCap(res.getAccommodation().getMinCap());
        dto.setCancelDuration(res.getAccommodation().getCancelDuration());
        dto.setAccommodationType(res.getAccommodation().getAccommodationType());
        dto.setAccommodationStatus(res.getAccommodation().getStatus());

        return dto;
    }
}