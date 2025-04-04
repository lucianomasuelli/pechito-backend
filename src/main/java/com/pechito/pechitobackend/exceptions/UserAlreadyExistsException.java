package com.pechito.pechitobackend.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists with same username or email");
    }
}
