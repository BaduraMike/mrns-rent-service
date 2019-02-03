package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class UserClientImplTest {

    @Autowired
    private UserClient userClient;

    @Test
    public void shouldFindAllUsersAndReturnNonZeroSizeOfCollection() throws Exception {
        Assert.assertNotEquals(0, userClient.findAll().getContent().size());
    }

    @Test
    public void shouldFindUserWithClientById() {
        User user = userClient.findById(1L);
        User user2 = userClient.findById(user.getId() + 1);

        Assert.assertNotEquals(user, user2);
    }
}