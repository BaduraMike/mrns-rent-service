package com.soft.mikessolutions.rentservice.controllers;

import com.soft.mikessolutions.rentservice.entities.User;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserClient userClient;

    UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/users")
    public Resources<Resource<User>> all() {
        return userClient.getUsers();
    }
}
