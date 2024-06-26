package com.tripster.project.mapper;

import com.tripster.project.dto.ReservationDTO;
import com.tripster.project.dto.ReservationGuestDTO;
import com.tripster.project.model.Reservation;
import com.tripster.project.model.enums.ReservationStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ReservationDTOMapper {
    public static Reservation fromDTOtoReservation(ReservationDTO dto) {

        Reservation reservation = new Reservation();
        //Reservation info
        reservation.setId(dto.getId());
        //reservation.setDeleted(false);
        reservation.setStart(dto.getStart());
        reservation.setEnd(dto.getEnd());
        reservation.setDuration(dto.getDuration());
        reservation.setGuestsNo(dto.getGuestsNo());
        reservation.setPrice(dto.getPrice());

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

        dto.setGuestId(res.getGuest().getId());
        dto.setAccommodationId(res.getAccommodation().getId());

        return dto;
    }
    public static ReservationGuestDTO fromGuestReservationToDTO(Reservation res,byte[] photo) {

        ReservationGuestDTO dto = new ReservationGuestDTO();
        dto.setId(res.getId());
        dto.setName(res.getAccommodation().getName());
        dto.setPhoto(photo);
        dto.setAddress(res.getAccommodation().getAddress().toString());
        dto.setDuration(res.getDuration());
        dto.setStatus(res.getStatus());
        dto.setTimeStamp(res.getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."))+" - "
        +res.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
        dto.setNumOfGuest(res.getGuestsNo());
        dto.setPrice(res.getPrice());
        dto.setReportable(res.getStatus().equals(ReservationStatus.ACCEPTED) && !LocalDate.now().isBefore(res.getEnd()));
        dto.setUserID(res.getAccommodation().getOwner().getUser().getId());

        return dto;
    }

    public static ReservationGuestDTO fromReservationToHostDTO(Reservation res, byte[] photo, int numOfCancelled) {

        ReservationGuestDTO dto = new ReservationGuestDTO();
        dto.setId(res.getId());
        dto.setName(res.getAccommodation().getName());
        dto.setPhoto(photo);
        dto.setAddress(res.getAccommodation().getAddress().toString());
        dto.setDuration(res.getDuration());
        dto.setStatus(res.getStatus());
        dto.setTimeStamp(res.getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."))+" - "
                +res.getEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
        dto.setNumOfGuest(res.getGuestsNo());
        dto.setPrice(res.getPrice());
        dto.setNumOfCancelled(numOfCancelled);
        dto.setGuest(res.getGuest().getUser().getEmail());
        dto.setUserID(res.getGuest().getUser().getId());

        dto.setReportable(res.getStatus().equals(ReservationStatus.ACCEPTED) && !LocalDate.now().isBefore(res.getEnd()));
        return dto;
    }
}
