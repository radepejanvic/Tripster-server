package com.tripster.project.service;

import com.tripster.project.model.Photo;
import com.tripster.project.repository.PhotoRepository;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> findAllByAccommodationId(Long accommodationId) {
        return photoRepository.findAllByAccommodationId(accommodationId);
    }

}
