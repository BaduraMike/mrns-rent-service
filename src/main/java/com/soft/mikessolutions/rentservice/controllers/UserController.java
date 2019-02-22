package com.soft.mikessolutions.rentservice.controllers;

import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   private UserClient userClient;

   UserController(UserClient userClient){
       this.userClient = userClient;
   }

    @GetMapping("/users/{id:\\d+}")
    public User one(@PathVariable Long id) {
        return userClient.findById(id);
    }
}
