package com.pechito.pechitobackend.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private WorkoutType type;
    @OneToMany
    private List<Section> sections;

    public WorkoutSession() {}

    public WorkoutSession(Long id, String name, String description, WorkoutType type, List<Section> sections) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.sections = sections;
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

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
