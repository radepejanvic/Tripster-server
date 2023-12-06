package com.tripster.project.service;

import com.tripster.project.model.Photo;
import com.tripster.project.repository.PhotoRepository;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Value(value="${photos}")
    private String directory;

    public List<byte[]> findAllByAccommodationId(Long accommodationId) {

        List<byte[]> bytes = new ArrayList<>();

        try {
            for (Photo photo : photoRepository.findAllByAccommodationId(accommodationId)) {
                bytes.add(Files.readAllBytes(new File(directory.concat(photo.getPath())).toPath()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }

}
