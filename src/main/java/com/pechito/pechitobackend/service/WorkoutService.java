package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.Section;
import com.pechito.pechitobackend.model.WorkoutSession;
import com.pechito.pechitobackend.model.WorkoutType;
import com.pechito.pechitobackend.repository.WorkoutSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService implements IWorkoutService{
    @Autowired
    private WorkoutSessionRepository workoutRepository;

    @Autowired
    private SectionService sectionService;

    @Override
    public WorkoutSession saveWorkout(WorkoutSession workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public Iterable<WorkoutSession> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public WorkoutSession getWorkoutById(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }

    @Override
    public void editWorkout(Long id, String name, String description, WorkoutType type) {
        WorkoutSession workout = this.getWorkoutById(id);
        if(name != null){
            workout.setName(name);
        }
        if(description != null){
            workout.setDescription(description);
        }
        if(type != null){
            workout.setType(type);
        }
        this.saveWorkout(workout);
    }

    @Override
    public void editWorkout(WorkoutSession workout) {
        this.saveWorkout(workout);
    }

    @Override
    public void addSection(Long workoutId, Long sectionId) {
        WorkoutSession workout = this.getWorkoutById(workoutId);
        Section section = sectionService.getSectionById(sectionId);
        workout.getSections().add(section);
        this.saveWorkout(workout);
    }

    @Override
    public void addSection(Long workoutId, Section section) {
        WorkoutSession workout = this.getWorkoutById(workoutId);
        workout.getSections().add(sectionService.saveSection(section)); //saves the section and adds it to the workout
        this.saveWorkout(workout);
    }

    @Override
    public void addExercise(Long workoutId, Long sectionId, Long exerciseId) {

    }

    @Override
    public void addExercise(Long workoutId, Long sectionId, Exercise exercise) {

    }
}
