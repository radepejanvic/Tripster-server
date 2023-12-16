package com.tripster.project.service.interfaces;

import com.tripster.project.model.Photo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    Photo findOne(Long id);

    void save(MultipartFile photoFile, Photo photo) throws IOException;

    void remove(Long id, String path);

    List<byte[]> findAllByAccommodationId(Long accommodationId);


    boolean hasPrimary(Long accommodationId);

    byte[] findPrimary(Long accommodationId);

}
