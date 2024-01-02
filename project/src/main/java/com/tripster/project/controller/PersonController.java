package com.tripster.project.controller;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.mapper.PersonCruDTOMapper;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.DeleteStatus;
import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.service.AccommodationService;
import com.tripster.project.service.ReservationServiceImpl;
import com.tripster.project.service.UserReviewService;
import com.tripster.project.service.interfaces.ConfirmationTokenService;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {
    
    @Autowired
    private UserService userService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Qualifier("hostServiceImpl")
    @Autowired
    private IPersonService  hostService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private UserReviewService userReviewService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PersonCruDTO>> getAll(){

        List<Person> persons = new ArrayList<Person>();
        persons.addAll(guestService.findAll());
        persons.addAll(hostService.findAll());
        List<PersonCruDTO> personCruDTOS = persons.stream()
                .map(PersonCruDTOMapper::fromPersonToDTO).toList();
        return new ResponseEntity<>(personCruDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonCruDTO> getById(@PathVariable Long id) {

        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Person person;

        switch(user.getUserType()) {
            case GUEST: person = guestService.findByUser(user); break;
            case HOST: person = hostService.findByUser(user); break;
            default: return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(PersonCruDTOMapper.fromPersonToDTO(person), HttpStatus.OK);
    }

    @GetMapping(value = "/hosts/{id}")
    public ResponseEntity<PersonCruDTO> getHostById(@PathVariable Long id) {

        Person person = hostService.findById(id);

        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Object[]> rating = userReviewService.countReviews(person.getUser().getId());
        PersonCruDTO dto = PersonCruDTOMapper.fromPersonToDTO(person);
        dto.setRate((double)rating.get(0)[0]);
        dto.setNumOfReviews((long)rating.get(0)[1]);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/update",consumes = "application/json")
    public ResponseEntity<PersonCruDTO> update(@RequestBody PersonCruDTO personCruDTO){

        Person person = PersonCruDTOMapper.fromDTOtoPerson(personCruDTO,"UPDATE");
        person.getUser().setStatus(UserStatus.ACTIVE);

        if (person.getUser().getUserType().equals(UserType.GUEST)){
            if (guestService.update(person) == null){
                return new ResponseEntity<>(new PersonCruDTO(),HttpStatus.BAD_REQUEST);
            }
            guestService.save(person);
        }else{
            if (hostService.update(person) == null){
                return new ResponseEntity<>(new PersonCruDTO(),HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(PersonCruDTOMapper.fromPersonToDTO(person), HttpStatus.CREATED);
    }



   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteStatus> deleteUser(@PathVariable Long id) {

        User user = userService.findOne(id);
        Person person;

        if (user != null) {
            if(user.getUserType().equals(UserType.GUEST)){
                if (!reservationService.getAllActiveForGuest(id).isEmpty()) {
                    return new ResponseEntity<>(DeleteStatus.HAS_RESERVATIONS, HttpStatus.PAYMENT_REQUIRED);
                }
                confirmationTokenService.deleteUserTokens(id);
                person = guestService.findByUser(user);
                guestService.remove(person.getId());
            }else{
                if(!reservationService.getAllActiveForHost(id).isEmpty()) {
                    return new ResponseEntity<>(DeleteStatus.HAS_RESERVATIONS, HttpStatus.PAYMENT_REQUIRED);
                }
                confirmationTokenService.deleteUserTokens(id);
                person = hostService.findByUser(user);
                accommodationService.removeAllByOwnerId(person.getId());
                hostService.remove(person.getId());
            }
            return new ResponseEntity<>(DeleteStatus.SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(DeleteStatus.NO_USER_FOUND, HttpStatus.NOT_FOUND);
        }

    }

}
