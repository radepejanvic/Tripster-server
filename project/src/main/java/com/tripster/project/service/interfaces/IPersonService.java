package com.tripster.project.service.interfaces;

import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IPersonService {
    Person save(Person person);

     Person findByUser(User user);
     List<Person> findAll();

     Page<Person> findAll(Pageable page);
     Person findById(Long id);
     void remove(Long id);

     Person update(Person person);
}
