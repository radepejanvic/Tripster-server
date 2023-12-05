package com.tripster.project.repository;

import com.tripster.project.model.ConfirmationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    Optional<ConfirmationToken> findByToken(String token);
    @Transactional
    @Modifying
    @Query("update ConfirmationToken c " +
            "set c.confirmedAt = :confirmedAt " +
            "where c.token = :token")
    int updateConfirmationTokenByToken(String token, LocalDateTime confirmedAt);
}
