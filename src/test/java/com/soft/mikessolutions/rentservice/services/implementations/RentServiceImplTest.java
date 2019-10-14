package com.soft.mikessolutions.rentservice.services.implementations;

import com.soft.mikessolutions.rentservice.clients.MachineClient;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.Rent;
import com.soft.mikessolutions.rentservice.services.RentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceImplTest {

    @Autowired
    private RentService rentService;

    @Autowired
    private MachineClient machineClient;

    @Autowired
    private UserClient userClient;

    @Test
    public void shouldAddMachineToExistingRent() {
        long existingId = rentService.findAll().size();
        Rent rent = rentService.findById(existingId);
        String machineSerialNumber = "EC14-49812";

        rentService.addMachineToRent(existingId, machineSerialNumber);

        Assert.assertEquals(machineSerialNumber,
                machineClient.getMachineBySerialNumber(machineSerialNumber).getContent().getSerialNumber());
    }

    @Test
    public void shouldAddUserToExistingRent() {
        long existingId = rentService.findAll().size();
        Rent rent = rentService.findById(existingId);
        String userEmail = "grazyna.idzzesz@warbud.pl";

        rentService.addUserToRent(existingId, userEmail);

        Assert.assertEquals(userEmail,
                userClient.getUserByEmail(userEmail).getContent().getEmail());
    }
}