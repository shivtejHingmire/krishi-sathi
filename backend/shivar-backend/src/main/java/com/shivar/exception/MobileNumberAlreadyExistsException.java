package com.shivar.exception;

/**
 * Thrown when a user tries to register
 * with a mobile number that already exists.
 */
public class MobileNumberAlreadyExistsException extends RuntimeException {

    public MobileNumberAlreadyExistsException(String message) {
        super(message);
    }

}