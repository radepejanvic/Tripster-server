package com.tripster.project.service;

import com.tripster.project.model.Accommodation;
import com.tripster.project.model.Settings;
import com.tripster.project.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    public Settings findByUserId(Long userId) {
        return settingsRepository.findByUserId(userId);
    }

}
