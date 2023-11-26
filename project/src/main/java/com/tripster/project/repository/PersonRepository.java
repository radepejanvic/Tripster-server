package com.tripster.project.repository;

import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

//    public Person findByUser(User user);
}
