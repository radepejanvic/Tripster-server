package com.tripster.project.repository;

import com.tripster.project.model.User;
import com.tripster.project.model.enums.UserStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailAndPassword (String email,String password);

    @Transactional
    @Modifying
    @Query("update User u " +
            "set u.status = :status " +
            "where u.id = :id")
    int updateStatus(Long id, UserStatus status);

}
