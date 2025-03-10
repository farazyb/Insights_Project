package com.sea.reporter;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ConnectionTimeOutException extends RuntimeException {

    public ConnectionTimeOutException(String message) {
        super(message);
    }
}
