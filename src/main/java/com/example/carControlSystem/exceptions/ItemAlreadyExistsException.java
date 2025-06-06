package com.example.carControlSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ItemAlreadyExistsException extends Exception {
    
    public ItemAlreadyExistsException(String message) {
        super(message);
    }

}
