package com.pfa.microserviceoffers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends RuntimeException{

    public UserBadRequestException() {
        super();
    }

    public UserBadRequestException(String message) {
        super(message);
    }
}
