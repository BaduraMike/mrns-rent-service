package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "user-service")
public interface UserClient {
    @GetMapping("/users")
    Resources<Resource<User>> getUsers();

    @GetMapping("/users/{id}")
    Resource<User> getUserById(@RequestParam("id") Long id);
}
