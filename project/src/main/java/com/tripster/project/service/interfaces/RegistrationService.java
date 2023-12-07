package com.tripster.project.service.interfaces;

import com.tripster.project.model.ConfirmationToken;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import jakarta.mail.MessagingException;

public interface RegistrationService {

    String register(Person person) throws MessagingException;

    String confirmToken(String token);

    ConfirmationToken generateToken(User user);
}
