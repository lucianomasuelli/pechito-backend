package com.pechito.pechitobackend.service;

import com.pechito.pechitobackend.exceptions.ExerciseAlreadyExistsException;
import com.pechito.pechitobackend.exceptions.ExerciseNotFoundException;
import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.model.WorkoutType;
import com.pechito.pechitobackend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ExerciseService implements IExerciseService{

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise saveExercise(Exercise exercise) {
        try{
            this.getExerciseById(exercise.getId());
            throw new ExerciseAlreadyExistsException(exercise.getId());
        } catch (ExerciseNotFoundException e){
            //If the exercise does not exist, it is created
            return exerciseRepository.save(exercise);
        }
    }

    @Override
    public Iterable<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    @Override
    public void deleteExercise(Long id) {
        this.getExerciseById(id); //Checks if the exercise exists
        exerciseRepository.deleteById(id);
    }

    @Override
    public void updateExercise(Exercise exercise) {
        this.saveExercise(exercise);
    }

    @Override
    public void editExercise(Long id, String name, String description, String videoUrl, Duration time, String repetitions, WorkoutType type) {
        Exercise exercise = this.getExerciseById(id);
        if(name != null){
            exercise.setName(name);
        }
        if(description != null){
            exercise.setDescription(description);
        }
        if(videoUrl != null){
            exercise.setVideoUrl(videoUrl);
        }
        if(time != null){
            exercise.setTime(time);
        }
        if(repetitions != null){
            exercise.setRepetitions(repetitions);
        }
        if(type != null){
            exercise.setType(type);
        }
        this.updateExercise(exercise);
    }
}
