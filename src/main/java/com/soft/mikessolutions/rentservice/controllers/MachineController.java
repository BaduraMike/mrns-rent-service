package com.soft.mikessolutions.rentservice.controllers;

import com.soft.mikessolutions.rentservice.clients.MachineClient;
import com.soft.mikessolutions.rentservice.entities.Machine;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rents")
public class MachineController {
    private MachineClient machineClient;

    MachineController(MachineClient machineClient) {
        this.machineClient = machineClient;
    }

    @GetMapping("/machines")
    public Resources<Resource<Machine>> allMachines() {
        return machineClient.getMachines();
    }

    @GetMapping("/machines/{id}")
    public Resource<Machine> oneMachine(@PathVariable("id") Long id) {
        return machineClient.getMachineById(id);
    }

}
