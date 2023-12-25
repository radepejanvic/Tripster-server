package com.tripster.project.repository;

import com.tripster.project.model.Guest;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {

    Person findByUser(User user);
}
