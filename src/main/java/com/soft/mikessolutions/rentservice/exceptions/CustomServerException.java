package com.soft.mikessolutions.rentservice.exceptions;

public class CustomServerException extends RuntimeException {
    public CustomServerException(String message) {
        super(message);
    }
}
