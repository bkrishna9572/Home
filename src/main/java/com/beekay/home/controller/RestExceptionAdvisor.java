package com.beekay.home.controller;

import com.beekay.home.exceptions.ResourceNotFoundException;
import com.beekay.home.exceptions.UniqueConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(Exception exception, WebRequest request){
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<Object> handleUniqueConstraintViolation(Exception exception, WebRequest request){
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

}
