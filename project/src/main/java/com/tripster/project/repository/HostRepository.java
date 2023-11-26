package com.tripster.project.repository;

import com.tripster.project.model.Host;
import com.tripster.project.model.Person;
import com.tripster.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host,Long> {
    public Person findByUser(User user);
}
