package com.tripster.project.controller;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.dto.UserDTO;
import com.tripster.project.dto.UserLoginDTO;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.mapper.PersonCruDTOMapper;
import com.tripster.project.mapper.UserDTOMapper;
import com.tripster.project.service.UserServiceImpl;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/person")
public class PersonController {
    
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private IPersonService personService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PersonCruDTO> saveUser(@RequestBody PersonCruDTO dto) {

        Person person = PersonCruDTOMapper.fromDTOtoPerson(dto);
        person = personService.save(person);
        return new ResponseEntity<>(PersonCruDTOMapper.fromPersonToDTO(person), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO userLoginDTO){

        User user = userService.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        UserDTO userDTO= UserDTOMapper.fromUsertoUserDTO(user);
        return new ResponseEntity<>(userDTO,HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        User user = userService.findOne(id);
        Person person = personService.findByUser(user);

        if (person != null) {
            personService.remove(person.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
