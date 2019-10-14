package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldGetUsersAndReturnNonZeroSizeOfCollection() {
        Assert.assertNotEquals(0, userClient.getUsers().getContent().size());
    }

    @Test
    public void shouldGetUserByExistingId() {
        Long existingId = (long) userClient.getUsers().getContent().size();
        Assert.assertNotNull(userClient.getUserById(existingId));
    }

    @Test
    public void shouldGetUserByEmail() {
        Long existingId = (long) userClient.getUsers().getContent().size();
        User userById = userClient.getUserById(existingId).getContent();
        String existingUserEmail = userById.getEmail();

        User userByEmail = userClient.getUserByEmail(existingUserEmail).getContent();

        Assert.assertEquals(existingUserEmail,userByEmail.getEmail());
    }
//TODO
    /*
    @Test
    public void shouldThrowCustomClientExceptionWithMessage404ForGetUserByNonExistingId() {
        Long nonExistingId = (long) userClient.getUsers().getContent().size() + 1;
        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("Resource you are trying to get from the client is not found");
        Assert.assertNotNull(userClient.getUserById(nonExistingId));
    }
    */
}
