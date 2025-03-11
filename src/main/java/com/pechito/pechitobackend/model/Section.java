package com.pechito.pechitobackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private int sets;
    @OneToMany
    private List<Exercise> exercises;

    public Section() {}

    public Section(Long id, String name, String description, int sets, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sets = sets;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
