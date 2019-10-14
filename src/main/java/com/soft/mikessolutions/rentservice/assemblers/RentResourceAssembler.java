package com.soft.mikessolutions.rentservice.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.soft.mikessolutions.rentservice.controllers.RentController;
import com.soft.mikessolutions.rentservice.entities.Rent;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class RentResourceAssembler implements ResourceAssembler<Rent, Resource<Rent>> {

    @Override
    public Resource<Rent> toResource(Rent rent) {
        return new Resource<>(rent,
                linkTo(methodOn(RentController.class).getRentById(rent.getId())).withSelfRel(),
                linkTo(methodOn(RentController.class).getAllRents()).withRel("rents"));
    }
}
