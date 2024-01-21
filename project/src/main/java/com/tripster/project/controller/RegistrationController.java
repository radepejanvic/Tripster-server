package com.tripster.project.controller;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.dto.TokenDTO;
import com.tripster.project.mapper.PersonCruDTOMapper;
import com.tripster.project.model.Person;
import com.tripster.project.service.interfaces.RegistrationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Person> register(@RequestBody PersonCruDTO dto) {

        Person person = PersonCruDTOMapper.fromDTOtoPerson(dto,"NEW");
        TokenDTO tokenDTO = new TokenDTO();

        try {
            person = registrationService.register(person);
        } catch (MessagingException e) {
            return new ResponseEntity<>(person,HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
