package com.soft.mikessolutions.rentservice.exceptions;

public class CustomClientException extends RuntimeException {
    public CustomClientException(String message) {
        super(message);
    }
}
