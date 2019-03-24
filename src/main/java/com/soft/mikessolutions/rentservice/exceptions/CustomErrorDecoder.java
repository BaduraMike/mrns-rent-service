package com.soft.mikessolutions.rentservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 404:
                return new ResourceNotFoundException("Resource you are trying to get from the client is not found");
        }
//        if (response.status() >= 400 && response.status() <= 499) {
//            return new CustomClientException(String.valueOf(response.status()));
//        }
//        if (response.status() >= 500 && response.status() <= 599) {
//            return new CustomServerException(String.valueOf(response.status()));
//        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
