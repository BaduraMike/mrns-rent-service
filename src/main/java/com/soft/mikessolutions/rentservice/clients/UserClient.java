package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import com.soft.mikessolutions.rentservice.fallbacks.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-service", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/users")
    Resources<Resource<User>> getUsers();

    @GetMapping("/users/{id}")
    Resource<User> getUserById(@PathVariable("id") Long id);

    @GetMapping(value = "/users/email/{email}")
    Resource<User> getUserByEmail(@PathVariable("email") String email);
}
