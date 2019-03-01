package com.soft.mikessolutions.rentservice.clients;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.soft.mikessolutions.rentservice.assemblers.DefaultUserResourceAssembler;
import com.soft.mikessolutions.rentservice.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.*;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClientImpl implements UserClient {
    private final User DEFAULT_USER = new User(0L, "John","Doe",
            null,"johndoe@doe.com", "000-000-000","pass","DEFAULT");

    private final Logger logger = LoggerFactory.getLogger(UserClientImpl.class);
    private final RestTemplate restTemplate;
    private final EurekaClient discoveryClient;
    private final DefaultUserResourceAssembler defaultUserResourceAssembler;

    private List<User> createDeafultUsersList(){
        List<User> defaultUsers = new ArrayList<>();
        defaultUsers.add(DEFAULT_USER);
        return defaultUsers;
    }

    private String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("user-service", false);
        return instance.getHomePageUrl();
    }

    @Autowired
    private UserClientImpl(RestTemplateBuilder restTemplateBuilder, EurekaClient discoveryClient,
                           DefaultUserResourceAssembler defaultUserResourceAssembler) {
        this.restTemplate = restTemplateBuilder.build();
        this.discoveryClient = discoveryClient;
        this.defaultUserResourceAssembler = defaultUserResourceAssembler;
    }

    @Override
    @HystrixCommand(fallbackMethod = "userFallback")
    public User findById(Long id) {
        return restTemplate.getForObject(serviceUrl() + "/users/" + id, User.class);
    }

    @Override
    @HystrixCommand(fallbackMethod = "usersFallback")
    public Resources<Resource<User>> findAll() throws Exception {
        URI uri = new URI(serviceUrl() + "/users");
        Traverson client = new Traverson(uri, MediaTypes.HAL_JSON_UTF8);

        Resources<Resource<User>> users = client
                .follow("users")
                .toObject(new TypeReferences.ResourcesType<Resource<User>>() {
                });
        return users;
    }

    @HystrixCommand
    public User userFallback(Long id, Throwable ex) {
        logger.warn("Fallback method with reason: " + ex.getMessage());
        return DEFAULT_USER;
    }

    @HystrixCommand
    public Resources<Resource<User>> usersFallback(Throwable ex) {
        logger.warn("Fallback method with reason: " + ex.getMessage());

        List<Resource<User>> defaultUsers = createDeafultUsersList().stream()
                .map(defaultUserResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(defaultUsers);
    }

}
