package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.Machine;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class MachineClientImplTest {

    @Autowired
    private MachineClient machineClient;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldGetMachinesAndReturnNonZeroSizeOfCollection() {
        Assert.assertNotEquals(0, machineClient.getMachines().getContent().size());
    }

    @Test
    public void shouldGetMachineByExistingId() {
        Long existingId = (long) machineClient.getMachines().getContent().size();
        Assert.assertNotNull(machineClient.getMachineById(existingId));
    }

    @Test
    public void shouldGetMachineBySerialNumber() {
        Long existingId = (long) machineClient.getMachines().getContent().size();
        Resource<Machine> machine = machineClient.getMachineById(existingId);

        String existingMachineSerialNumber = machine.getContent().getSerialNumber();
        Resource<Machine> machine2 = machineClient.getMachineBySerialNumber(existingMachineSerialNumber);

        Assert.assertEquals(machine.getContent().getIdentityNumber(), machine2.getContent().getIdentityNumber());
    }

}