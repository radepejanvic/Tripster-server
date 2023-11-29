package com.tripster.project.service;

import com.tripster.project.dto.PersonCruDTO;
import com.tripster.project.model.*;
import com.tripster.project.repository.AddressRepository;
import com.tripster.project.repository.HostRepository;
import com.tripster.project.repository.UserRepository;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements IPersonService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HostRepository hostRepository;


    @Override
    public Person save(Person person) {
        Host host = (Host)person;
        Address address = addressRepository.save(person.getAddress());
        host.setAddress(address);
        User user = person.getUser();
        user = userRepository.save(user);
        host.setUser(user);
        host = hostRepository.save(host);
        return host;
    }

    @Override
    public Person findByUser(User user) {
        return hostRepository.findByUser(user);
    }

    @Override
    public Person findById(Long id) {
        return hostRepository.findById(id).orElseGet(null);
    }

    @Override
    public void remove(Long id) {
        hostRepository.deleteById(id);
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

    @Override
    public List<Person> findAll() {
        return hostRepository.findAll().stream().map(host -> (Person) host).toList();
    }

    @Override
    public Page<Person> findAll(Pageable page) {
        return (Page<Person>) hostRepository.findAll(page).stream().map(host -> (Person) host).toList();
    }
}
