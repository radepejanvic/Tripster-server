package com.tripster.project.mapper;

import com.tripster.project.dto.ReservationDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationDTOMapper {
    public static Reservation fromDTOtoReservation(ReservationDTO dto) {

        Reservation reservation = new Reservation();
        //Reservation info
        reservation.setId(dto.getId());
        reservation.setDeleted(false);
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
        dto.setGuestUserEmail(res.getGuest().getUser().getEmail());
        dto.setGuestUserType(res.getGuest().getUser().getUserType());
        dto.setGuestUserStatus(res.getGuest().getUser().getStatus());
        dto.setGuestUserPhone(res.getGuest().getPhone());

        //Accommodation info
        dto.setAccmId(res.getAccommodation().getId());
        Accommodation acc = res.getAccommodation();
        dto.setAccmName(acc.getName());
        //Owner info
        dto.setAccmOwnerEmail(res.getAccommodation().getOwner().getUser().getEmail());
        dto.setAccmOwnerName(res.getAccommodation().getOwner().getName());
        dto.setAccmOwnerSurname(res.getAccommodation().getOwner().getSurname());
        dto.setAccmOwnerPhone(res.getAccommodation().getOwner().getPhone());
        //Accommodation details info
        dto.setAmenities(res.getAccommodation().getAmenities());
        //dto.setPhoto(res.getAccommodation().getPhoto());
        dto.setMaxCap(res.getAccommodation().getMaxCap());
        dto.setMinCap(res.getAccommodation().getMinCap());
        dto.setCancelDuration(res.getAccommodation().getCancelDuration());
        dto.setAccommodationType(res.getAccommodation().getType());
        dto.setAccommodationStatus(res.getAccommodation().getStatus());

        return dto;
    }
}
