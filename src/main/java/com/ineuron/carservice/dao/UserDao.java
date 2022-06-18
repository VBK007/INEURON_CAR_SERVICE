package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {

    Optional<User> findByUsernameAndIsEnabledTrue(String username);

}
