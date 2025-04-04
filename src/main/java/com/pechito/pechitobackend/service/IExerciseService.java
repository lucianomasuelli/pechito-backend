package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.*;
import com.pechito.pechitobackend.model.Exercise;

import java.time.Duration;
import java.util.List;

public interface IExerciseService {
    public Exercise saveExercise(Exercise exercise);
    public Iterable<Exercise> getAllExercises();
    public Exercise getExerciseById(Long id);
    public void deleteExercise(Long id);
    public void updateExercise(Exercise exercise);
    public void editExercise(Long id, String name, String description, String videoUrl, Duration time, String repetitions, WorkoutType type);
}
