package com.tripster.project.service.interfaces;

import com.tripster.project.model.ConfirmationToken;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;

public interface RegistrationService {

    String register(Person person);

    String confirmToken(String token);

    ConfirmationToken generateToken(User user);
}
