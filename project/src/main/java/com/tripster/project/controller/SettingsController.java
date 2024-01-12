package com.tripster.project.controller;


import com.tripster.project.dto.SettingsDTO;
import com.tripster.project.mapper.SettingsDTOMapper;
import com.tripster.project.model.Settings;
import com.tripster.project.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/users/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PutMapping(consumes = "application/json")
    public ResponseEntity<SettingsDTO> changeSettings(@RequestBody SettingsDTO dto) {

        Settings settings = settingsService.findByUserId(dto.getUserId());

        if(settings == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SettingsDTOMapper.setFromDTO(settings, dto);
        settingsService.save(settings);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<SettingsDTO> getSettings(@PathVariable Long userId) {

        Settings settings = settingsService.findByUserId(userId);

        if(settings == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(SettingsDTOMapper.fromSettingsToDTO(settings), HttpStatus.OK);
    }

}
