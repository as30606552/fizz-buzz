package com.example.fizzbuzz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalTurnException extends RuntimeException {

    public IllegalTurnException(String message) {
        super(message);
    }
}
