package com.pechito.pechitobackend.exceptions;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(Long id) {
        super("Exercise not found with id: " + id);
    }
}
