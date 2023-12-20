package com.tripster.project.service;

import com.tripster.project.model.ConfirmationToken;
import com.tripster.project.repository.ConfirmationTokenRepository;
import com.tripster.project.service.interfaces.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public Optional<ConfirmationToken> findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmationTokenByToken(token, LocalDateTime.now());
    }

    public void deleteUserTokens(Long userID) {
        confirmationTokenRepository.deleteUserTokens(userID);
    }
}
