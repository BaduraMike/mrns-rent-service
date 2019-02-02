package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.*;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class UserClientImpl implements UserClient {
    private RestTemplate restTemplate;

    @Value("${user.server.address}")
    private String userServerAddress;

    @Autowired
    private UserClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public User findById(Long id) {
        return restTemplate.getForObject(userServerAddress + "/users/" + id, User.class);
    }

    @Override
    public Resources<Resource<User>> findAll() throws Exception {
        URI uri = new URI(userServerAddress + "/users");
        Traverson client = new Traverson(uri, MediaTypes.HAL_JSON_UTF8);
        Resources<Resource<User>> users = client
                .follow("users")
                .toObject(new TypeReferences.ResourcesType<Resource<User>>() {
                });
        return users;
    }
}
