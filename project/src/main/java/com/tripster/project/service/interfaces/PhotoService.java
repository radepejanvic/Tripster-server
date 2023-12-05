package com.tripster.project.service.interfaces;

import com.tripster.project.model.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> findAllByAccommodationId(Long accommodationId);

}
