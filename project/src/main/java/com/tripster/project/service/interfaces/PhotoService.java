package com.tripster.project.service.interfaces;

import com.tripster.project.model.Photo;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface PhotoService {

    List<byte[]> findAllByAccommodationId(Long accommodationId);

}
