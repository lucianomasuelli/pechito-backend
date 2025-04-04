package com.pechito.pechitobackend.exceptions;

public class ExerciseAlreadyExistsException extends RuntimeException {
    public ExerciseAlreadyExistsException(Long id) {
        super("Exercise already exists with id: " + id);
    }
}
