package com.soft.mikessolutions.rentservice.controllers;

import com.soft.mikessolutions.rentservice.entities.User;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rents")
public class UserController {
    private UserClient userClient;

    UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/users")
    public Resources<Resource<User>> all() {
        return userClient.getUsers();
    }

    @GetMapping("/users/{id}")
    public Resource<User> one(@PathVariable("id") Long id) {
        return userClient.getUserById(id);
    }

}
