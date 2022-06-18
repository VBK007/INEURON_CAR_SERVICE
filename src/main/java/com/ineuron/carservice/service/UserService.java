package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.UserDao;
import com.ineuron.carservice.exception.InvalidDataException;
import com.ineuron.carservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loadUserByUsername(final String username) {

        final Optional<User> user = userDao.findByUsernameAndIsEnabledTrue(username);

        if (!user.isPresent()) {
            throw new InvalidDataException("Username or Password invalid");
        }
        return user.get();
    }

    public User loadUserById(final UUID id) {

        final Optional<User> user = userDao.findById(id);

        if (!user.isPresent()) {
            throw new InvalidDataException("User not found");
        }
        return user.get();
    }

    public User saveOrUpdate(final User user) {
        user.setFailedPasswordAttempts(0);
        user.setIsAccountExpired(false);
        user.setIsCredentialExpired(false);
        user.setIsAccountLocked(false);
        user.setPassword(passwordEncoder.encode(user.getUsername()));
        return userDao.save(user);
    }

    public User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}

