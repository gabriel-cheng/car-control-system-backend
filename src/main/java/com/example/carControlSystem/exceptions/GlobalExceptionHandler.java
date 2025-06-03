package com.example.carControlSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An unexpected error ocurred. Please, try again later!");
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> handleItemAlreadyExists(ItemAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
    }

    @ExceptionHandler(ResourceCannotBeNullException.class)
    public ResponseEntity<String> handleResourceCannotBeNullException(ResourceCannotBeNullException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
    }

}
