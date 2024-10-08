package com.fdifrison.catan.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameMalformedException extends RuntimeException {
    public GameMalformedException(String message) {
        super(message);
    }
}
