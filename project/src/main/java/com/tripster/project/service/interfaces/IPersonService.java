package com.tripster.project.service.interfaces;

import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.stereotype.Service;


public interface IPersonService {
    public Person save(Person person);

    public Person findByUser(User user);

    public Person findById(Long id);
    public void remove(Long id);
}
