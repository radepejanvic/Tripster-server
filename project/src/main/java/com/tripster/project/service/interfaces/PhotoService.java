package com.tripster.project.service.interfaces;

import com.tripster.project.model.Photo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    List<byte[]> findAllByAccommodationId(Long accommodationId);

    void save(MultipartFile photoFile, Photo photo) throws IOException;

    boolean hasPrimary(Long accommodationId);

}
