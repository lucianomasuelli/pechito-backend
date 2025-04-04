package com.pechito.pechitobackend.exceptions;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(Long id) {
        super("Workout not found with id: " + id);
    }

    public WorkoutNotFoundException(String name) {
        super("Workout not found with name: " + name);
    }
}
