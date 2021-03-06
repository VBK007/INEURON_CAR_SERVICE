package com.ineuron.carservice.controller;

import com.ineuron.carservice.model.User;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getLoggedInUser/{username}")
    public User getLoggedInUser(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }

    @GetMapping("/user/getUser/{id}")
    public User getUser(@PathVariable("id") UUID id) {
        return userService.loadUserById(id);
    }

    @PostMapping("/user/save")
    public SuccessResponse saveUser(@RequestBody User user) {
        return new SuccessResponse(userService.saveOrUpdate(user));
    }
}
