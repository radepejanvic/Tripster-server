package com.tripster.project.repository;

import com.tripster.project.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    Optional<ConfirmationToken> findByToken(String token);

    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

    int updateConfirmationTokenByToken(String token, LocalDateTime confirmedAt);
}
