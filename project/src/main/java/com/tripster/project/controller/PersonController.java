package com.tripster.project.controller;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.dto.UserDTO;
import com.tripster.project.dto.UserLoginDTO;
import com.tripster.project.model.Host;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.mapper.PersonCruDTOMapper;
import com.tripster.project.mapper.UserDTOMapper;
import com.tripster.project.service.UserServiceImpl;
import com.tripster.project.service.interfaces.IPersonService;
import com.tripster.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {
    
    @Autowired
    private IUserService userService;

    @Qualifier("guestServiceImpl")
    @Autowired
    private IPersonService guestService ;

    @Qualifier("hostServiceImpl")
    @Autowired
    private IPersonService  hostService;

    @GetMapping
    public ResponseEntity<List<PersonCruDTO>> getAll(){

        List<Person> persons = new ArrayList<Person>();
        persons.addAll(guestService.findAll());
        persons.addAll(hostService.findAll());
        List<PersonCruDTO> personCruDTOS = persons.stream()
                .map(PersonCruDTOMapper::fromPersonToDTO).toList();
        return new ResponseEntity<>(personCruDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PersonCruDTO> saveUser(@RequestBody PersonCruDTO dto) {

        Person person = PersonCruDTOMapper.fromDTOtoPerson(dto,"NEW");
        try {
            if (person.getUser().getUserType().equals(UserType.GUEST)){
                person = guestService.save(person);
            }else{
                person = hostService.save(person);
            }
        }catch (Exception exception){
            return new ResponseEntity<>(new PersonCruDTO(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(PersonCruDTOMapper.fromPersonToDTO(person), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO userLoginDTO){

        User user = userService.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        UserDTO userDTO= UserDTOMapper.fromUsertoUserDTO(user);
        return new ResponseEntity<>(userDTO,HttpStatus.FOUND);
    }

    @PutMapping(value = "/update",consumes = "application/json")
    public ResponseEntity<PersonCruDTO> update(@RequestBody PersonCruDTO personCruDTO){

        Person person = PersonCruDTOMapper.fromDTOtoPerson(personCruDTO,"UPDATE");

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




    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        User user = userService.findOne(id);
        Person person;

        if (user != null) {
            if(user.getUserType().equals(UserType.GUEST)){
                person = guestService.findByUser(user);
                guestService.remove(person.getId());
            }else{
                person = hostService.findByUser(user);
                hostService.remove(person.getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
