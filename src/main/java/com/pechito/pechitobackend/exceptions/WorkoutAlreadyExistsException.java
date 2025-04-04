package com.pechito.pechitobackend.exceptions;

public class WorkoutAlreadyExistsException extends RuntimeException {
    public WorkoutAlreadyExistsException(String name) {
        super("Workout already exists with name: " + name);
    }
}
