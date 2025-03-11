package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.model.WorkoutType;

public interface IWorkoutService {
    public WorkoutSession saveWorkout(WorkoutSession workout);
    public Iterable<WorkoutSession> getAllWorkouts();
    public WorkoutSession getWorkoutById(Long id);
    public void deleteWorkout(Long id);
    public void editWorkout(Long id, String name, String description, WorkoutType type);
    public void editWorkout(WorkoutSession workout);
    public void addSection(Long workoutId, Long sectionId);
    public void addSection(Long workoutId, Section section);
    public void addExercise(Long workoutId, Long sectionId, Long exerciseId);
    public void addExercise(Long workoutId, Long sectionId, Exercise exercise);
}
