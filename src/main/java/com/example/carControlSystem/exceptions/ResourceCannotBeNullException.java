package com.example.carControlSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceCannotBeNullException extends Exception {

    public ResourceCannotBeNullException(String message) {
        super(message);
    }

}