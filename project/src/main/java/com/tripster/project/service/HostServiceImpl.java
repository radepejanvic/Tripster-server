package com.tripster.project.service;

import com.tripster.project.model.*;
import com.tripster.project.repository.AddressRepository;
import com.tripster.project.repository.HostRepository;
import com.tripster.project.repository.UserRepository;
import com.tripster.project.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<Person> findAll() {
        return hostRepository.findAll().stream().map(host -> (Person) host).toList();
    }

    @Override
    public Page<Person> findAll(Pageable page) {
        return (Page<Person>) hostRepository.findAll(page).stream().map(host -> (Person) host).toList();
    }
}
