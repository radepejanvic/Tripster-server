package com.tripster.project.service;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.model.ConfirmationToken;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.service.interfaces.ConfirmationTokenService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Qualifier("hostServiceImpl")
    @Autowired
    private IPersonService  hostService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Override
    public String register(Person person)  {
//        TODO: email validator

        if (person.getUser().getUserType().equals(UserType.GUEST)){
            person = guestService.save(person);
        }else{
            person = hostService.save(person);
        }
        ConfirmationToken token = generateToken(person.getUser());
        confirmationTokenService.save(token);
//        TODO: email sender
        return token.getToken();
    }

    @Override
    public String confirmToken(String token) {
        return null;
    }

    @Override
    public ConfirmationToken generateToken(User user) {
        return new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(1),
                user
        );
    }
}
