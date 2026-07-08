package com.shivar.exception;

/**
 * Thrown when a user tries to register
 * with an email address that already exists.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}