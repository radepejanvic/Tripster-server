package com.tripster.project.service;

import com.tripster.project.model.Address;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import com.tripster.project.repository.AddressRepository;
import com.tripster.project.repository.PersonRepository;
import com.tripster.project.repository.UserRepository;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        Address address = addressRepository.save(person.getAddress());
        person.setAddress(address);
        User user = userRepository.save(person.getUser());
        person.setUser(user);
        person = personRepository.save(person);
        return person;
    }

    @Override
    public Person findByUser(User user) {
//        return personRepository.findByUser(user);
        return new Person();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseGet(null);
    }

    @Override
    public void remove(Long id) {
        personRepository.deleteById(id);
    }
}
