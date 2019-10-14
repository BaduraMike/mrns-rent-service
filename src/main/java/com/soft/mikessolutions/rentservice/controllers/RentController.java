package com.soft.mikessolutions.rentservice.controllers;

import com.soft.mikessolutions.rentservice.assemblers.RentResourceAssembler;
import com.soft.mikessolutions.rentservice.clients.MachineClient;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.Machine;
import com.soft.mikessolutions.rentservice.entities.Rent;
import com.soft.mikessolutions.rentservice.entities.User;
import com.soft.mikessolutions.rentservice.services.RentService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/rents")
public class RentController {
    private final MachineClient machineClient;
    private final UserClient userClient;
    private final RentResourceAssembler rentAssembler;
    private final RentService rentService;

    RentController(MachineClient machineClient, UserClient userClient,
                   RentResourceAssembler rentAssembler, RentService rentService) {
        this.machineClient = machineClient;
        this.userClient = userClient;
        this.rentAssembler = rentAssembler;
        this.rentService = rentService;
    }

    @GetMapping
    public Resources<Resource<Rent>> getAllRents() {
        List<Resource<Rent>> rents = rentService.findAll().stream()
                .map(rentAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(rents,
                linkTo(methodOn(RentController.class).getAllRents()).withRel("rents"));
    }

    @GetMapping("/{rentId}")
    public Resource<Rent> getRentById(@PathVariable("rentId") Long id) {
        Rent rent = rentService.findById(id);

        return rentAssembler.toResource(rent);
    }

    @GetMapping("/{rentId}/machines/{machineId}")
    public Resource<Machine> getMachineOfRent(@PathVariable("machineId") Long machineId) {
        return machineClient.getMachineById(machineId);
    }

    @GetMapping("/{rentId}/users/{userId}")
    public Resource<User> getUserOfRent(@PathVariable("userId") Long userId) {
        return userClient.getUserById(userId);
    }

    @PutMapping("/{rentId}/machines")
    public ResponseEntity<?> addMachineToRent(@RequestParam("serialNumber") String serialNumber,
                                              @PathVariable("rentId") Long rentId) {

        Rent rent = rentService.addMachineToRent(rentId, serialNumber);
        Resource<Rent> resource = rentAssembler.toResource(rent);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(resource);
    }

    @PutMapping("/{rentId}/users")
    public ResponseEntity<?> addUserToRent(@RequestParam("email") String email,
                                              @PathVariable("rentId") Long rentId) {

        Rent rent = rentService.addUserToRent(rentId, email);
        Resource<Rent> resource = rentAssembler.toResource(rent);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(resource);
    }
}
