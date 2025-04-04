package com.pechito.pechitobackend.exceptions;

public class SectionAlreadyExistsException extends RuntimeException {
    public SectionAlreadyExistsException(String name) {
        super("Section with name " + name + " already exists");
    }
}
