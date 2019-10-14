package com.soft.mikessolutions.rentservice.fallbacks;

import com.soft.mikessolutions.rentservice.assemblers.DefaultUserResourceAssembler;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserClientFallback implements UserClient {
    private final Logger logger = LoggerFactory.getLogger(UserClientFallback.class);
    private final DefaultUserResourceAssembler defaultUserResourceAssembler;

    UserClientFallback(DefaultUserResourceAssembler defaultUserResourceAssembler) {
        this.defaultUserResourceAssembler = defaultUserResourceAssembler;
    }

    private final User DEFAULT_USER = new User(0L, "John", "Doe",
            null, "johndoe@doe.com", "000-000-000", "pass", "DEFAULT");

    private List<User> createDefaultUsersList() {
        List<User> defaultUsers = new ArrayList<>();
        defaultUsers.add(DEFAULT_USER);
        return defaultUsers;
    }

    @Override
    public Resources<Resource<User>> getUsers() {
        logger.warn("Client fallback");

        List<Resource<User>> defaultUsers = createDefaultUsersList().stream()
                .map(defaultUserResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(defaultUsers);
    }

    @Override
    public Resource<User> getUserById(Long id) {
        logger.warn("Client fallback");
        return defaultUserResourceAssembler.toResource(DEFAULT_USER);
    }

    @Override
    public Resource<User> getUserByEmail(String email) {
        logger.warn("Client fallback");
        return defaultUserResourceAssembler.toResource(DEFAULT_USER);
    }
}
