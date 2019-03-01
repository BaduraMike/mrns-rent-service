package com.soft.mikessolutions.rentservice.assemblers;

import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user);
    }
}
