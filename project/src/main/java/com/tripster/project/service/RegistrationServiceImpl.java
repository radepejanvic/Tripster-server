package com.tripster.project.service;


import com.tripster.project.model.ConfirmationToken;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.service.interfaces.*;
import com.tripster.project.utils.VerificationEmailBuilder;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PasswordEncoder encoder;

    @Value(value="${template}")
    private String templatePath;

    @Transactional
    @Override
    public Person register(Person person) throws MessagingException {

        String password = person.getUser().getPassword();
        person.getUser().setPassword(encoder.encode(password));

        if (person.getUser().getUserType().equals(UserType.GUEST)){
            person = guestService.save(person);
        }else{
            person = hostService.save(person);
        }
        ConfirmationToken token = generateToken(person.getUser());
        confirmationTokenService.save(token);
        String link = "http://localhost:8080/api/registration/confirm?token=" + token.getToken();

        String verificationEmail = VerificationEmailBuilder.build(templatePath, person.getName(), link);

        emailSender.send(person.getUser().getEmail(),"Tripster: Account verification", verificationEmail);
        return person;
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .findByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.updateStatus(confirmationToken.getUser().getId(), UserStatus.ACTIVE );
        return "confirmed";
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
