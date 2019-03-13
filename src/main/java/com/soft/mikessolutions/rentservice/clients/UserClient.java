package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "user-service")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    Resources<Resource<User>> getUsers();
}
