package com.pechito.pechitobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private boolean isPublic;
    @Enumerated(EnumType.STRING)
    private WorkoutType type;
    @OneToMany
    private List<Section> sections;
    @OneToMany
    private List<User> editors;
    @OneToMany
    private List<User> viewers;
}
