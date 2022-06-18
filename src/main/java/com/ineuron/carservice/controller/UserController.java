package com.ineuron.carservice.controller;

import com.ineuron.carservice.model.User;
import com.ineuron.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getLoggedInUser/{username}")
    public User getLoggedInUser(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable("id") UUID id) {
        return userService.loadUserById(id);
    }
}
