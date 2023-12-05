package com.tripster.project.service.interfaces;

import com.tripster.project.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void save(ConfirmationToken confirmationToken);

    Optional<ConfirmationToken> findByToken(String token);

    int setConfirmedAt(String token);
    
}
