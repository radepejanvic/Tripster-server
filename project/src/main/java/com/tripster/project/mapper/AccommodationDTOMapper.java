package com.tripster.project.mapper;

import com.tripster.project.dto.AccommodationCardAdminDTO;
import com.tripster.project.dto.AccommodationCardGuestDTO;
import com.tripster.project.dto.AccommodationCardHostDTO;
import com.tripster.project.dto.AccommodationDTO;
import com.tripster.project.model.Accommodation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccommodationDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public AccommodationDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Accommodation fromDTOtoAccommodation(AccommodationDTO dto) {
        return modelMapper.map(dto, Accommodation.class);
    }

    public static AccommodationDTO fromAccommodationToDTO(Accommodation accommodation) {
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    public static AccommodationCardAdminDTO fromAccommodationToAdminDTO(Accommodation accommodation) {
        return modelMapper.map(accommodation, AccommodationCardAdminDTO.class);
    }

    public static AccommodationCardHostDTO fromAccommodationToHostDTO(Accommodation accommodation) {
        return modelMapper.map(accommodation, AccommodationCardHostDTO.class);
    }

    public static AccommodationCardGuestDTO fromAccommodationToGuestDTO(Accommodation accommodation) {
        return modelMapper.map(accommodation, AccommodationCardGuestDTO.class);
    }
}


