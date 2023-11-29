package com.tripster.project.service;

import com.tripster.project.model.Address;
import com.tripster.project.model.Guest;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import com.tripster.project.model.enums.UserType;
import com.tripster.project.repository.*;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements IPersonService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GuestRepository guestRepository;


    @Override
    public Person save(Person person) {
        Guest guest = (Guest) person;
        Address address = addressRepository.save(person.getAddress());
        guest.setAddress(address);
        User user = person.getUser();
        user = userRepository.save(user);
        guest.setUser(user);
        guest = guestRepository.save(guest);
        return guest;
    }

    @Override
    public Person findByUser(User user) {
        return guestRepository.findByUser(user);
    }

    @Override
    public List<Person> findAll() {
        return guestRepository.findAll().stream().map(guest -> (Person) guest).toList();
    }

    @Override
    public Page<Person> findAll(Pageable page) {
        return (Page<Person>) guestRepository.findAll(page).stream().map(guest -> (Person) guest).toList();
    }

    @Override
    public Person findById(Long id) {
        return guestRepository.findById(id).orElseGet(null);
    }

    @Override
    public void remove(Long id) {
        guestRepository.deleteById(id);
    }

    @Override
    public Person update(Person person) {
        Person oldPerson = findById(person.getId());
        if (oldPerson == null){
            return null;
        }
        String pass = oldPerson.getUser().getPassword();
        person.getUser().setPassword(pass);
        person.getAddress().setId(oldPerson.getAddress().getId());
        person.getUser().setId(oldPerson.getUser().getId());
        return save(person);
    }
}
