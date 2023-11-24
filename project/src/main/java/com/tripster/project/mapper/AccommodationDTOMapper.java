package com.tripster.project.mapper;

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

    public static AccommodationDTO fromAccommodationToDTO(Accommodation dto) {
        return modelMapper.map(dto, AccommodationDTO.class);
    }
}


