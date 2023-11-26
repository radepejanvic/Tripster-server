package com.tripster.project.service.interfaces;

import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IPersonService {
    public Person save(Person person);

    public Person findByUser(User user);
    public List<Person> findAll();

    public Page<Person> findAll(Pageable page);
    public Person findById(Long id);
    public void remove(Long id);
}
