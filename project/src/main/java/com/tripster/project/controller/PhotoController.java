package com.tripster.project.controller;

import com.tripster.project.service.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Value("${photos}")
    private String directory;


    @GetMapping(value = "/{accommodationId}")
    public ResponseEntity<List<byte[]>> getPhotos(@PathVariable Long accommodationId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(photoService.findAllByAccommodationId(accommodationId));
    }

}
