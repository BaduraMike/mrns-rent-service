package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public interface UserClient {
    User findById(Long id);

    Resources<Resource<User>> findAll() throws Exception;
}
