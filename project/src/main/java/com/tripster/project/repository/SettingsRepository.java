package com.tripster.project.repository;

import com.tripster.project.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface SettingsRepository extends JpaRepository<Settings, Long> {

    @Query("select s " +
            "from Settings s " +
            "where s.user.id = :userId")
    Settings findByUserId(Long userId);

}
