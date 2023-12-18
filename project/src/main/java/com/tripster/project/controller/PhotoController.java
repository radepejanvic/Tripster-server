package com.tripster.project.controller;

import com.tripster.project.dto.PhotoDTO;
import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Photo;
import com.tripster.project.model.enums.AccommodationStatus;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AccommodationService accommodationService;

    @Value("${photos}")
    private String directory;


    @GetMapping(value = "/{accommodationId}")
    public ResponseEntity<List<byte[]>> getPhotos(@PathVariable Long accommodationId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.findAllByAccommodationId(accommodationId));
    }

    @GetMapping(value = "/crud/{accommodationId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosWithId(@PathVariable Long accommodationId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.findAllByAccommodationIdWithId(accommodationId));
    }

    @PostMapping("/{accommodationId}")
    public ResponseEntity<Integer> uploadPhotos(@RequestParam("photo") MultipartFile[] photos, @PathVariable Long accommodationId) throws IOException {

        Accommodation accommodation = accommodationService.findOne(accommodationId);
        boolean hasPrimary = photoService.hasPrimary(accommodationId);

        if(accommodation.getStatus() == AccommodationStatus.ACTIVE) {
            accommodation.setTimeStamp(LocalDateTime.now());
            accommodation.setStatus(AccommodationStatus.UPDATED);
        }

        for (MultipartFile photoFile : photos) {
            Photo photo = new Photo(!hasPrimary ? "primary" : "secondary", "jpg", accommodation);
            hasPrimary = true;
            photoService.save(photoFile, photo);
        }

        return new ResponseEntity<>(photos.length, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {

        Photo photo = photoService.findOne(id);

        if (photo != null) {
            photoService.remove(id, photo.getPath());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping()
    public ResponseEntity<Integer> batchDelete(@RequestBody List<Long> ids) {

        int deleted = 0;
        Photo photo;

        for (Long id : ids) {
            photo = photoService.findOne(id);
            if (photo != null) {
                photoService.remove(id, photo.getPath());
                deleted++;
            }
        }

        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
