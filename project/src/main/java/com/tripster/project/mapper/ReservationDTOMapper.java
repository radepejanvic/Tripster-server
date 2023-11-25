package com.tripster.project.mapper;

import com.tripster.project.dtos.ReservationDTO;
import com.tripster.project.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationDTOMapper {
    private static ModelMapper modelMapper;
    @Autowired
    public ReservationDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Reservation fromDTOtoReservation(ReservationDTO dto) {    return modelMapper.map(dto, Reservation.class);}
    public static ReservationDTO fromReservationToDTO(Reservation res) {    return modelMapper.map(res, ReservationDTO.class);}
}
