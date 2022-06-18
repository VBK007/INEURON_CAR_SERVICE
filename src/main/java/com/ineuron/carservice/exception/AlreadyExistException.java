package com.ineuron.carservice.exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(final String message) {
        super(message);
    }
}
