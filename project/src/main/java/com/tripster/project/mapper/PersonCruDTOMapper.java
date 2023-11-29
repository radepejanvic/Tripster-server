
package com.tripster.project.mapper;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.model.*;

import com.tripster.project.model.enums.UserType;
import org.springframework.stereotype.Component;

@Component
public class PersonCruDTOMapper {


    public static Person fromDTOtoPerson(PersonCruDTO personDTO,String status) {

        Person person;
        if(personDTO.getUserType().equals(UserType.GUEST)){
                person = new Guest();
        }else{
                person = new Host();
        }

        if (status == "UPDATE"){
            person.setId(personDTO.getId());
        }

        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setPhone(personDTO.getPhone());

        User user = new User();

        user.setEmail(personDTO.getEmail());
        user.setPassword(personDTO.getPassword());
        user.setUserType(personDTO.getUserType());
        user.setStatus(personDTO.getStatus());

        Address address = new Address();

        address.setCountry(personDTO.getCountry());
        address.setCity(personDTO.getCity());
        address.setZipCode(personDTO.getZipCode());
        address.setStreet(personDTO.getStreet());
        address.setNumber(personDTO.getNumber());

        person.setAddress(address);
        person.setUser(user);

        return person;
    }
    public static PersonCruDTO fromPersonToDTO(Person person) {

        PersonCruDTO personDTO = new PersonCruDTO();

        personDTO.setId(person.getId());
        personDTO.setEmail(person.getUser().getEmail());
        personDTO.setUserType(person.getUser().getUserType());
        personDTO.setStatus(person.getUser().getStatus());

        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setPhone(person.getPhone());


        personDTO.setCountry(person.getAddress().getCountry());
        personDTO.setCity(person.getAddress().getCity());
        personDTO.setZipCode(person.getAddress().getZipCode());
        personDTO.setStreet(person.getAddress().getStreet());
        personDTO.setNumber(person.getAddress().getNumber());

        return personDTO;
    }

}

