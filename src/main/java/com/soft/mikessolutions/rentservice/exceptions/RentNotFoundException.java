package com.soft.mikessolutions.rentservice.exceptions;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(Long id) {
        super("Rent with {id} = " + id + " is not found.");
    }
}
