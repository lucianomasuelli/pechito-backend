package com.pechito.pechitobackend.controller;

import com.pechito.pechitobackend.model.Exercise;
import com.pechito.pechitobackend.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
public class ExerciseController {

    @Autowired
    private IExerciseService exerciseService;

    @GetMapping("/exercises/all")
    public Iterable<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/exercises/get/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping("/exercises/add")
    public void addExercise(@RequestBody Exercise exercise) {
        exerciseService.saveExercise(exercise);
    }

    @DeleteMapping("/exercises/delete/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    @PutMapping("/exercises/update/{id}")
    public void updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        exerciseService.updateExercise(exercise);
    }

    @PutMapping("/exercises/edit/{id}")
    public void editExercise(@PathVariable Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String videoUrl,
                             @RequestParam(required = false) Duration time,
                             @RequestParam(required = false) String repetitions) {

        exerciseService.editExercise(id, name, description, videoUrl, time, repetitions);
    }


}
